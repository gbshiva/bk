package com.wyndham.ari.service;

import com.wyndham.ari.helper.BookingProperties;


public interface iBookingService {
	public void preProcess(BookingProperties prop);
	public void aggregate(BookingProperties prop);
	

}
