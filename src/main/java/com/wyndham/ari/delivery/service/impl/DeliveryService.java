package com.wyndham.ari.delivery.service.impl;

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
import com.wyndham.ari.helper.DeliveryProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.helper.ToolkitService;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;
import com.wyndham.ari.service.iDeliveryService;

public class DeliveryService implements iDeliveryService {
	static Logger logger = Logger.getLogger(DeliveryService.class);
	static final Timer timerdelivery = Instrumentation.getRegistry().timer(
			MetricRegistry.name(DeliveryService.class, "DELIVERY"));
	Timer.Context context = null;
	static Byte NEW = 1;
	static Byte INPROGRESS = 2;
	static Byte COMPLETED = 3;

	public void delivery(DeliveryProperties prop) {
		logger.info("Starting booking com delivery process");

		Cache AggCache = CacheService.getCache(prop.AGGREGATOR_CACHE_NAME);
		Queue deliveryQueue = ToolkitService.getInstance(
				prop.DELIVERY_TOOLKIT_URI).getQueue(prop.DELIVERY_QUEUE);

		if (prop.DELIVERY_STATS)
			context = timerdelivery.time();
		logger.info("Queue Size =" + deliveryQueue.size());
		boolean forever = true;
		int i = 0;
		
		while (i < prop.DELIVERY_BATCH) {
			try {

				Delivery dlvry = (Delivery) deliveryQueue.remove();
				dlvry.setMessageStatusId(COMPLETED);
				Element e = new Element(dlvry.getReqId(), dlvry);
				e.setTimeToLive(2400);
				AggCache.putWithWriter(e);
			} catch (Exception ex) {
				forever = false;
			}
		}
		if (prop.DELIVERY_STATS)
			context.stop();
		logger.info("Completed booking delivery process");

	}

}
