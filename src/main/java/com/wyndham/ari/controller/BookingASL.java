package com.wyndham.ari.controller;

/**
 * Hello world!
 *
 */
import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class BookingASL 
{
	static Logger logger = Logger.getLogger(BookingASL.class);
	private static void usage(){
		logger.error("BookingASL <properties file>");
		System.exit(1);
	}
	
	
	
    public static void main( String[] args )
    {
    	if (args.length < 1) usage();
    	logger.debug("Using property file "+args[0]);
    	BookingProperties props = new BookingProperties(args[0]);
    	iBookingService bookingService = (iBookingService)new BookingService();
    	bookingService.preProcess(props);
    	while(true){
    		try {
				Thread.sleep(60000);
				bookingService.preProcess(props);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }



	
}
