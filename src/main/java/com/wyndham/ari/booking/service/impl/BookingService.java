package com.wyndham.ari.booking.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import net.sf.ehcache.search.query.QueryManager;
import net.sf.ehcache.search.query.QueryManagerBuilder;

import org.apache.log4j.Logger;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorCacheService;
import com.wyndham.ari.dao.Delivery;
import com.wyndham.ari.dao.DeliveryAdapter;
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.helper.ToolkitService;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class BookingService implements iBookingService {
	static Logger logger = Logger.getLogger(BookingService.class);
	static final Timer timerPreAgg = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "PREAGGQUERY"));
	static Timer timerAgg = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "AGGREGATETOTAL"));
	static Timer timerPreDelivery = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "THROTTLER"));
	static long currentTheardID=0;
	static Byte NEW=1;
	static Byte INPROGRESS=2;
	static Byte COMPLETED=3;
	
	
	
	Timer.Context context=null;
	//Step 6 ref: Doc
	public void preProcessOld(BookingProperties prop) {
		logger.info("Starting booking com pre aggregator process. Assigning to Agg thread ID" + currentTheardID);
		Cache cache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query preprocessQuery = qm.createQuery("select key,value from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where aggregate_flag=(byte)1  order by pkey asc limit "
				+ prop.PREAGG_BATCH_SIZE);
		
		
		
		if (prop.PREAGG_STATS)
			context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				PreAgg preAggElement = (PreAgg) result.getValue();
				
				preAggElement.setAggrStatusId(INPROGRESS);
				preAggElement.setThreadId(currentTheardID);
				cache.put(new Element(result.getKey(), preAggElement));
				//cache.remove(result.getKey());
			}
		}
		
		if (prop.PREAGG_STATS) context.stop();
		logger.info("Completed booking com pre aggregator process for thread id" +currentTheardID +"processed a total of "+ results.size()+" items.");
		results.discard();
		
		if (currentTheardID < prop.AGGREGATOR_THREAD_POOL) 
			currentTheardID++;
		else
			currentTheardID=0;
	}

	
	public void preProcess(BookingProperties prop) {
		try {
		logger.info("Starting booking com pre aggregator process.");
		Cache pCache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		Cache dCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);
		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query preprocessQuery = qm.createQuery("select key from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where aggregate_flag=(byte)1  order by pkey asc limit "
				+ prop.PREAGG_BATCH_SIZE);
		
		
		
		if (prop.PREAGG_STATS)
			context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		ArrayList<String> dataList = new ArrayList();
		
		for (Result result : results.all()) {
			if (result.getKey() != null) {
				
					dataList.add((String)result.getKey());
				//pCache.remove(result.getKey());
			}
		}
		
		
		if (prop.PREAGG_STATS) context.stop();
		int TOTAL_NUM_ELEMENTS=results.size();
		logger.info("Completed preagg query processed a total of "+ TOTAL_NUM_ELEMENTS+" items.");
		results.discard();
		
		logger.info("Starting Aggregator Process");
		if(prop.AGGREGATOR_STATS)
			context = timerPreAgg.time();
		ExecutorService executor = Executors.newFixedThreadPool(prop.AGGREGATOR_THREAD_POOL);
		int BATCH_SIZE = TOTAL_NUM_ELEMENTS/prop.AGGREGATOR_THREAD_POOL;
		for (int i = 0 ; i< prop.AGGREGATOR_THREAD_POOL ; i++){
			Runnable worker = new AggrCacheService(dataList, i*BATCH_SIZE, (i+1)*BATCH_SIZE, pCache, dCache);
			executor.execute(worker);
		}
		executor.shutdown();
	      while (!executor.isTerminated())
	      {
	        
				Thread.sleep(50L);
			
	      }
	      if(prop.AGGREGATOR_STATS)
	      context.stop();
	      logger.info("Completed Aggregator Process");
		
		} catch (Exception e) {
			logger.error(e);
		}
		
	}
	
	
	
	
	
	
	
	public void aggregate(BookingProperties prop,int threadID) {
		logger.info("Starting booking com aggregator process for threadid " + threadID);
		Cache preAggCache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		Cache AggCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);

		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();
		
		Query preprocessQuery = qm.createQuery("select key,value from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where ( aggregate_flag = (byte)1 and threadid = (long) "+threadID +" ) order by pkey asc ");
		if(prop.AGGREGATOR_STATS)
		context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				PreAgg preAggElement = (PreAgg) result.getValue();
				preAggElement.setAggrStatusId(COMPLETED);
				preAggElement.setThreadId(999999L);
				preAggCache.put(new Element(result.getKey(), preAggElement));
				Delivery dlvry =  new DeliveryAdapter().convert(preAggElement);
				AggCache.put(new Element(dlvry.getReqId(), dlvry));
			}
		}
		
		if(prop.AGGREGATOR_STATS)
		context.stop();
		results.discard();
		logger.info("Completed booking Aggregator process for threadid" + threadID +" processed a total of "+ results.size()+" items.");
		
		
	}
	

	public void throttle(BookingProperties prop) {
		logger.info("Starting booking com Throttler process");
		
		Cache AggCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);
		//logger.info("Establishing toolkit connection to " + prop.DELIVERY_TOOLKIT_URI);
		Queue deliveryQueue = ToolkitService.getInstance(BookingProperties.DELIVERY_TOOLKIT_URI).getQueue(prop.DELIVERY_QUEUE);

		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query deliveryQuery = qm.createQuery("select key,value from "
				+ prop.AGGREGATOR_CACHE_NAME
				+ " where message_status=(byte)1 order by source_time_stamp limit "
				+ prop.PREDELIVERY_BATCH_SIZE);

		if(prop.PREDELIVERY_STATS)
		context = timerPreDelivery.time();
		Results results = deliveryQuery.end().execute();
		logger.info("Completed Throttler query. Start moving to queue");
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				Delivery deliveryElement = (Delivery) result.getValue();
				deliveryQueue.add(deliveryElement);
				deliveryElement.setMessageStatusId(INPROGRESS);;
				AggCache.put(new Element(result.getKey(), deliveryElement));
			}
		}
		
		logger.info("Completed booking Throttler process processed a total of "+ results.size()+" items.");
		logger.info("Queue Size = "+ deliveryQueue.size() );
		if(prop.PREDELIVERY_STATS)
		context.stop();
		results.discard();
		
	}

	
	
	
	
	
	
	

}
