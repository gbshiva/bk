package com.wyndham.ari.controller;

/**
 * Booking ASL
 *
 */
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.helper.CacheService.Cacheget;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class BookingASL {
	static Logger logger = Logger.getLogger(BookingASL.class);

	private static void usage() {
		logger.error("BookingASL <properties file>");
		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length < 1)
			usage();
		logger.debug("Using property file " + args[0]);
		BookingProperties props = new BookingProperties(args[0]);
		preAgg(props);
	}

	private static void preAgg(BookingProperties props) {
		
		if (props.PREAGG_STATS)
			Instrumentation.start();
		
		ExecutorService executor = Executors.newFixedThreadPool(props.AGGREGATOR_THREAD_POOL+1);
		// Start the PreAggregate Process
		Runnable preAggThread = new Thread(new PreAggASL(props));
		executor.execute(preAggThread);

		// Start Aggregate Threads
		
		for (int i = 0; i < props.AGGREGATOR_THREAD_POOL; i++) {
		        Runnable worker = new AggASL(props,i);
		        executor.execute(worker);
		      
		}
		
		try {
			new Thread().sleep(props.PREAGG_PROCESS_WAIT_INTERVAL_MINS*60*1000);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
		executor.shutdown();
		
	
	}

	


}
