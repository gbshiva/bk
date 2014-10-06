package com.wyndham.ari.controller;

/**
 * CariASL
 *
 */
import org.apache.log4j.Logger;

import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class CariASL 
{
	static Logger logger = Logger.getLogger(CariASL.class);
	private static void usage(){
		logger.error("CariASL <properties file>");
		
		System.exit(1);
	}
	
	
	
    public static void main( String[] args )
    {
    	if (args.length < 1) usage();
    	logger.debug("Using property file "+args[0]);
    	CariProperties props = new CariProperties(args[0]);
    	iCariPreAggregatorService cariService = (iCariPreAggregatorService)new CariPreAggregatorService();
    	if (props.STATS) Instrumentation.start();
    	cariService.load(props);
    	
    	boolean loop=true;
    	int count=0;
    	String original = props.PROPERTYID;
    	while(loop){
    		try {
				Thread.sleep(1000);
				props.PROPERTYID=original+count;
				cariService.load(props);				
				count++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	
    }



	
}
