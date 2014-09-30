package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.ThreadController;
import com.wyndham.ari.service.iBookingService;

public class Throttler implements Runnable {
	private BookingProperties props;
	static Logger logger = Logger.getLogger(Throttler.class);

	public Throttler(BookingProperties iprops) {
		props = iprops;
	}

	public void run() {
		iBookingService bookingService = (iBookingService) new BookingService();
		while (ThreadController.isController()) {
			try {
				bookingService.throttle(props);
				Thread.sleep(props.PREDELIVERY_WAIT_INTERVAL);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}
}

