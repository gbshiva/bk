package com.wyndham.ari.booking.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
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
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class BookingService implements iBookingService {
	static Logger logger = Logger.getLogger(BookingService.class);
	static final Timer timerPreAgg = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "bookingpreagg"));
	static Timer timerAgg = Instrumentation.getRegistry().timer(
			MetricRegistry.name(BookingService.class, "bookingagg"));
	
	Timer.Context context=null;
	public void preProcess(BookingProperties prop) {
		logger.info("Starting booking com pre aggregator process");
		Cache cache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query preprocessQuery = qm.createQuery("select key,value from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where aggregate_flag=0  order by pkey asc limit "
				+ prop.BATCH_SIZE);
		if (prop.STATS)
			context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				PreAgg preAggElement = (PreAgg) result.getValue();
				preAggElement.setAGGREGATE_FLAG(1);
				preAggElement.setTHREAD_ID(1);
				cache.put(new Element(result.getKey(), preAggElement));
			}
		}
		if (prop.STATS) context.stop();
		logger.info("Completed booking com pre aggregator process");

	}

	public void aggregate(BookingProperties prop) {
		logger.info("Starting booking com aggregator process");
		Cache preAggCache = CacheService.getCache(prop.PREAGGREGATOR_CACHE_NAME);
		Cache AggCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);

		QueryManager qm = QueryManagerBuilder.newQueryManagerBuilder()
				.addAllCachesCurrentlyIn(CacheService.getCacheManager())
				.build();

		Query preprocessQuery = qm.createQuery("select key,value from "
				+ prop.PREAGGREGATOR_CACHE_NAME
				+ " where aggregate_flag=1 and   order by pkey  ");
		if(prop.STATS)
		context = timerPreAgg.time();
		Results results = preprocessQuery.end().execute();
		for (Result result : results.all()) {
			if (result.getKey() != null && result.getValue() != null) {
				PreAgg preAggElement = (PreAgg) result.getValue();
				preAggElement.setAGGREGATE_FLAG(2);
				preAggElement.setTHREAD_ID(0);
				preAggCache.put(new Element(result.getKey(), preAggElement));
				AggCache.put(new Element(result.getKey(), preAggElement));
			}
		}
		if(prop.STATS)
		context.stop();
		logger.info("Completed booking aggregator process");
		
		
	}

}
