package com.wyndham.ari.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.wyndham.ari.controller.CariASL;

public class BookingProperties {	
	static Logger logger = Logger.getLogger(BookingProperties.class);
	public static int NUM_THREADS = 1;
	public static int BATCH_SIZE =10000;
	
	public static String PREAGGREGATOR_CACHE_NAME;
	public static String AGGREGATOR_CACHE_NAME;
	public static boolean STATS=false;
	
	
	
	public  BookingProperties(String props){
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
			NUM_THREADS= Integer.parseInt(bookprops.getProperty("NUM_THREADS"));
			BATCH_SIZE=Integer.parseInt(bookprops.getProperty("BATCH_SIZE"));
			PREAGGREGATOR_CACHE_NAME=bookprops.getProperty("PREAGGREGATOR_CACHE_NAME");
			AGGREGATOR_CACHE_NAME=bookprops.getProperty("AGGREGATOR_CACHE_NAME");
			if (bookprops.getProperty("STATS","false").compareToIgnoreCase("true") == 0){
				STATS=true;
			}
			
			
			
			logger.debug("Found following properties " + NUM_THREADS +" "+BATCH_SIZE +" " + " "+ PREAGGREGATOR_CACHE_NAME+ " "+ AGGREGATOR_CACHE_NAME);
			
			
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
