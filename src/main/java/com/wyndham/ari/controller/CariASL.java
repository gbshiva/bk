package com.wyndham.ari.controller;

/**
 * Hello world!
 *
 */
import org.apache.log4j.Logger;

import com.wyndham.ari.cari.service.impl.CariPreAggregatorService;
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
    	while(loop){
    		try {
				Thread.sleep(1000);
				cariService.load(props);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    }



	
}
