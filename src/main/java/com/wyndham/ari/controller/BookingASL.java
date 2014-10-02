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
import com.wyndham.ari.helper.ThreadController;
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

		int threadpool = 0;
		if (props.PREAGG)
			threadpool++;
		/*
		if (props.AGG)
			threadpool += props.AGGREGATOR_THREAD_POOL;
			*/
		if (props.PREDELIVERY)
			threadpool++;
		logger.debug("Starting Thread pool " + threadpool);
		ExecutorService executor = Executors.newFixedThreadPool(threadpool);
		if (props.PREAGG) {
			logger.info("Initiating Pre Aggregator.");
			// Start the PreAggregate Process
			Runnable preAggThread = new Thread(new PreAggASL(props));
			executor.execute(preAggThread);
		}
		/**
		if (props.AGG) {
			logger.info("Initiating Aggregrator");
			// Start Aggregate Threads
			for (int i = 0; i < props.AGGREGATOR_THREAD_POOL; i++) {
				Runnable worker = new AggASL(props, i);
				executor.execute(worker);

			}
		}
		**/
		
		if (props.PREDELIVERY) {
			logger.info("Initiating Throttler.");
			Runnable preDeliveryThread = new Thread(new Throttler(props));
			executor.execute(preDeliveryThread);
		}

		try {
			long sleep = props.PREAGG_PROCESS_WAIT_INTERVAL_MINS * 60 * 1000;
			logger.info("Sleeping for "+sleep);
					Thread.sleep(sleep);
			ThreadController.setController(false);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		
		logger.info("Shutting down the process");
		executor.shutdown();
		
		while (!executor.isTerminated())
	      {
	        try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
	      }
		

	}

}
