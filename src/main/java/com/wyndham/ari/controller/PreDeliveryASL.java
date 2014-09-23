package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.service.iBookingService;

public class PreDeliveryASL implements Runnable {
	private BookingProperties props;
	static Logger logger = Logger.getLogger(PreDeliveryASL.class);

	public PreDeliveryASL(BookingProperties iprops) {
		props = iprops;
	}

	public void run() {
		iBookingService bookingService = (iBookingService) new BookingService();
		while (true) {
			try {
				bookingService.predelivery(props);
				Thread.sleep(props.PREAGG_WAIT_INTERVAL);
			} catch (InterruptedException e) {
				logger.error(e);
			}
		}
	}
}

