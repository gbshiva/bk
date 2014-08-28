package com.wyndham.ari.controller;

/**
 * Booking ASL
 *
 */
import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class BookingASL 
{
	static Logger logger = Logger.getLogger(BookingASL.class);
	private static void usage(){
		logger.error("BookingASL <properties file> <role> [threadID]");
		logger.error("role - preagg|agg");
		logger.error("if role is agg specify the current threadID");
		System.exit(1);
	}
	
	
	
    public static void main( String[] args )
    {
    	if (args.length < 2) usage();
    	logger.debug("Using property file "+args[0]);
    	BookingProperties props = new BookingProperties(args[0]);
    	if (args[1] == "preagg"){
    		preAgg(props);
    	}else if (args[1] == "agg"){
    		if (args.length < 3) usage();
    		int threadID =  Integer.parseInt(args[2]);
    		agg(props,threadID);

    	}
    }
    
    
    	private static void preAgg(BookingProperties props){
    	if (props.PREAGG_STATS) Instrumentation.start();
    	iBookingService bookingService = (iBookingService)new BookingService();

    	
    	while(true){
    		try {
				bookingService.preProcess(props);
				Thread.sleep(props.PREAGG_WAIT_INTERVAL);

    		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error(e);
				
			}
    	}
    	}
    	
    	
    	private static void agg(BookingProperties props, int threadID){
        	if (props.AGGREGATOR_STATS) Instrumentation.start();
        	iBookingService bookingService = (iBookingService)new BookingService();

        	while(true){
        		try {
    	        	bookingService.aggregate(props,threadID);
    				Thread.sleep(props.AGGREGATOR_WAIT_INTERVAL);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				//e.printStackTrace();
    				logger.error(e);
    				
    			}
        	}

    		
    		
    		
    		
    	}
    	
    	
    }



	

