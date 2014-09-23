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
import com.wyndham.ari.dao.Delivery;
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
			MetricRegistry.name(BookingService.class, "bookingpreagg"));
	static Timer timerAgg = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "bookingagg"));
	static long currentTheardID=0;
	static Byte status_preagg=1;
	static Byte status_agg=2;
	
	Timer.Context context=null;
	//Step 6 ref: Doc
	public void preProcess(BookingProperties prop) {
		logger.info("Starting booking com pre aggregator process. Assigning to Agg thread ID" + currentTheardID);
		Cache cache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query preprocessQuery = qm.createQuery("select key,value from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where aggregate_flag=(byte)0  order by pkey asc limit "
				+ prop.PREAGG_BATCH_SIZE);
		
		
		
		if (prop.PREAGG_STATS)
			context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				PreAgg preAggElement = (PreAgg) result.getValue();
				
				preAggElement.setAggrStatusId(status_preagg);
				preAggElement.setThreadId(currentTheardID);
				cache.put(new Element(result.getKey(), preAggElement));
				cache.remove(result.getKey());
			}
		}
		
		if (prop.PREAGG_STATS) context.stop();
		logger.info("Completed booking com pre aggregator process for thread id" +currentTheardID);
		if (currentTheardID < prop.AGGREGATOR_THREAD_POOL) 
			currentTheardID++;
		else
			currentTheardID=0;
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
				preAggElement.setAggrStatusId(status_agg);
				preAggElement.setThreadId(0L);
				preAggCache.put(new Element(result.getKey(), preAggElement));
				Delivery dlvry = new Delivery(preAggElement);
				AggCache.put(new Element(dlvry.getKey(), dlvry));
			}
		}
		if(prop.AGGREGATOR_STATS)
		context.stop();
		logger.info("Completed booking Aggregator process for threadid" + threadID);
		
		
	}
	

	public void predelivery(BookingProperties prop) {
		logger.info("Starting booking com pre delivery process");
		
		Cache AggCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);
		Queue deliveryQueue = ToolkitService.getInstance(prop.DELIVER_TOOLKIT_URI).getQueue(prop.DELIVERY_QUEUE);

		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query deliveryQuery = qm.createQuery("select key,value from "
				+ prop.AGGREGATOR_CACHE_NAME
				+ " where message_status='new' order by source_time_stamp");

		if(prop.AGGREGATOR_STATS)
		context = timerPreAgg.time();
		Results results = deliveryQuery.end().execute();
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				Delivery deliveryElement = (Delivery) result.getValue();
				deliveryQueue.add(deliveryElement);
				deliveryElement.setMESSAGE_STATUS("INPROGRESS");
				AggCache.put(new Element(result.getKey(), deliveryElement));
				
			}
		}
		if(prop.AGGREGATOR_STATS)
		context.stop();
		logger.info("Completed booking pre delivery process");
		
		
	}

	
	
	
	
	
	
	

}
