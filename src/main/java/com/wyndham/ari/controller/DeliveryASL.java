package com.wyndham.ari.controller;

/**
 * DeliveryASL
 *
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.DeliveryProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.helper.ThreadController;


public class DeliveryASL {
	static Logger logger = Logger.getLogger(DeliveryASL.class);

	private static void usage() {
		logger.error("DeliveryASL <properties file>");

		System.exit(1);
	}

	public static void main(String[] args) {
		if (args.length < 1)
			usage();
		Instrumentation.start();
		logger.debug("Using property file " + args[0]);
		DeliveryProperties props = new DeliveryProperties(args[0]);
		ExecutorService executor = Executors
				.newFixedThreadPool(props.DELIVERY_THREAD_POOL);
		for (int i = 0; i < props.DELIVERY_THREAD_POOL; i++) {
			Runnable worker = new Delivery(props);
			executor.execute(worker);

		}
		try {
			long sleep = props.PROCESS_WAIT_INTERVAL_MINS * 60 * 1000;
			logger.info("Sleeping for " + sleep);
			Thread.sleep(sleep);
			ThreadController.setController(false);
		} catch (InterruptedException e) {
			logger.error(e);
		}

		logger.info("Shutting down the process");
		executor.shutdown();

		while (!executor.isTerminated()) {
			try {
				Thread.sleep(50L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				logger.error(e);
			}
		}

		CacheService.shutDown();

	}

}
