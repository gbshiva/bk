package com.wyndham.ari.controller;

import org.apache.log4j.Logger;

import com.wyndham.ari.booking.service.impl.BookingService;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
import com.wyndham.ari.helper.BookingProperties;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.helper.ThreadController;
import com.wyndham.ari.service.iBookingService;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class Cari implements Runnable {
	private CariProperties props;
	static Logger logger = Logger.getLogger(Cari.class);

	public Cari(CariProperties iprops) {
		props = iprops;
	}

	public void run() {
		iCariPreAggregatorService cariService = (iCariPreAggregatorService)new CariPreAggregatorService();
    	if (props.STATS) Instrumentation.start();
    	cariService.load(props);
    	
    	boolean loop=true;
    	int count=0;
    	while(loop){
    		try {
				Thread.sleep(1000);
				props.PROPERTYID=props.PROPERTYID+count;
				cariService.load(props);				
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}
}

