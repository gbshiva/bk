package com.wyndham.ari.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.wyndham.ari.controller.CariASL;

public class DeliveryProperties {	
	static Logger logger = Logger.getLogger(DeliveryProperties.class);
	public static String AGGREGATOR_CACHE_NAME;	
	public static String DELIVERY_QUEUE="bookingcomqueue";
	public static String DELIVER_TOOLKIT_URI="toolkit:terracotta://localhost:9510";
	public static boolean DELIVERY_STATS=true;
	
	public  DeliveryProperties(String props){
		Properties bookprops = new Properties();
		BufferedReader bufferedTextIn  = null;
		try {
			String fileName = getClass().getClassLoader().getResource(props).getPath();
			logger.debug("Full path of property file"+ fileName);
			
			bufferedTextIn = new BufferedReader(new FileReader(fileName));
			if ( bufferedTextIn == null ){
				logger.error("Unable to read property file "+ fileName);
				System.exit(1);
			}
			bookprops.load(bufferedTextIn);
			AGGREGATOR_CACHE_NAME=bookprops.getProperty("AGGREGATOR_CACHE_NAME");
			if (bookprops.getProperty("DELIVERY_STATS","false").compareToIgnoreCase("true") == 0){
				DELIVERY_STATS=true;
			}
			DELIVERY_QUEUE=bookprops.getProperty("DELIVERY_QUEUE");
			DELIVER_TOOLKIT_URI=bookprops.getProperty("DELIVER_TOOLKIT_URI");
			
			logger.debug("Found following properties " + AGGREGATOR_CACHE_NAME);
			
			
		} catch(Exception ex){
			logger.error(ex);
		}finally{
			if (bufferedTextIn != null)
				try {
					bufferedTextIn.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					logger.error(e);
				}
		}
	}
	
	
	
	
	
	

}
