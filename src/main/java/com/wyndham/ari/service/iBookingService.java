package com.wyndham.ari.service;

import com.wyndham.ari.helper.BookingProperties;


public interface iBookingService {
	//Ref Step 6 from Doc.
	public void preProcess(BookingProperties prop);
	//Ref Step 7 from Doc.
	public void aggregate(BookingProperties prop,int threadID);
	//Ref Step 8 & 9
	public void predelivery(BookingProperties prop);
	

}
