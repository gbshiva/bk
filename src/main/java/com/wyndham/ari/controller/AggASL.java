package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.ThreadController;
import com.wyndham.ari.service.iBookingService;

public class AggASL implements Runnable {
	private BookingProperties props;
	private int threadid;
	static Logger logger = Logger.getLogger(AggASL.class);
	

	public AggASL(BookingProperties iprops, int ithreadid) {
		props = iprops;
		threadid = ithreadid;
	}

	public void run() {
		iBookingService bookingService = (iBookingService) new BookingService();
		while (ThreadController.isController()) {
			try {
				bookingService.aggregate(props, threadid);
				Thread.sleep(props.AGGREGATOR_WAIT_INTERVAL);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}
}
