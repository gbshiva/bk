package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.ThreadController;
import com.wyndham.ari.service.iBookingService;

public class PreAggASL implements Runnable {
	private BookingProperties props;
	static Logger logger = Logger.getLogger(PreAggASL.class);

	public PreAggASL(BookingProperties iprops) {
		props = iprops;
	}

	public void run() {
		iBookingService bookingService = (iBookingService) new BookingService();
		while (ThreadController.isController()) {
			try {
				bookingService.preProcess(props);
				Thread.sleep(props.PREAGG_WAIT_INTERVAL);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}
}

