package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.delivery.service.impl.DeliveryService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.DeliveryProperties;
import com.wyndham.ari.helper.ThreadController;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iDeliveryService;

public class Delivery implements Runnable {
	private DeliveryProperties props;
	static Logger logger = Logger.getLogger(Delivery.class);

	public Delivery(DeliveryProperties iprops) {
		props = iprops;
	}

	public void run() {
		iDeliveryService deliveryService = (iDeliveryService) new DeliveryService();
		while (ThreadController.isController()) {
			try {
				deliveryService.delivery(props);
				Thread.sleep(DeliveryProperties.DELIVERY_WAIT);
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
			}
		}
	}
}

