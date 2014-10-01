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
	
	public static boolean PREAGG=false;
	public static boolean AGG=false;
	public static boolean PREDELIVERY=false;
	//public static int NUM_THREADS = 1;
	public static int PREAGG_BATCH_SIZE =10000;
	public static int AGGREGATOR_THREAD_POOL=1;
	public static int PREAGG_WARMUP_COUNT=3;
	public static int AGGREGATOR_WARMUP_COUNT=3;

	public static int PREAGG_WAIT_INTERVAL=60000;
	public static int AGGREGATOR_WAIT_INTERVAL=30000;
	public static int PREDELIVERY_WAIT_INTERVAL=30000;

	
	public static String PREAGGREGATOR_CACHE_NAME;
	public static String AGGREGATOR_CACHE_NAME;
	public static boolean PREAGG_STATS=false;
	public static boolean AGGREGATOR_STATS=false;
	public static boolean PREDELIVERY_STATS=false;
	
	
	public static int PREAGG_PROCESS_WAIT_INTERVAL_MINS=10;
	
	public static String DELIVERY_QUEUE="bookingcomqueue";
	public static String DELIVERY_TOOLKIT_URI="toolkit:terracotta://localhost:9510";
	
	
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
			PREAGG_PROCESS_WAIT_INTERVAL_MINS=Integer.parseInt(bookprops.getProperty("PREAGG_PROCESS_WAIT_INTERVAL_MINS"));
			AGGREGATOR_THREAD_POOL= Integer.parseInt(bookprops.getProperty("AGGREGATOR_THREAD_POOL"));
			PREAGG_BATCH_SIZE=Integer.parseInt(bookprops.getProperty("PREAGG_BATCH_SIZE"));
			PREAGG_WARMUP_COUNT=Integer.parseInt(bookprops.getProperty("PREAGG_WARMUP_COUNT"));
			PREAGG_WAIT_INTERVAL=Integer.parseInt(bookprops.getProperty("PREAGG_WAIT_INTERVAL"));
			AGGREGATOR_WAIT_INTERVAL=Integer.parseInt(bookprops.getProperty("AGGREGATOR_WAIT_INTERVAL"));
			
			PREAGGREGATOR_CACHE_NAME=bookprops.getProperty("PREAGGREGATOR_CACHE_NAME");
			AGGREGATOR_CACHE_NAME=bookprops.getProperty("AGGREGATOR_CACHE_NAME");
			if (bookprops.getProperty("PREAGG_STATS","false").compareToIgnoreCase("true") == 0){
				PREAGG_STATS=true;
			}
			if (bookprops.getProperty("AGGREGATOR_STATS","false").compareToIgnoreCase("true") == 0){
				AGGREGATOR_STATS=true;
			}
			if (bookprops.getProperty("PREDELIVERY_STATS","false").compareToIgnoreCase("true") == 0){
				PREDELIVERY_STATS=true;
			}
			
			
			DELIVERY_QUEUE=bookprops.getProperty("DELIVERY_QUEUE");
			DELIVERY_TOOLKIT_URI=bookprops.getProperty("DELIVERY_TOOLKIT_URI");
			
			
			if (bookprops.getProperty("PREAGG","false").compareToIgnoreCase("true") == 0){
				PREAGG=true;
			}
			
			if (bookprops.getProperty("AGG","false").compareToIgnoreCase("true") == 0){
				AGG=true;
			}
			
			if (bookprops.getProperty("PREDELIVERY","false").compareToIgnoreCase("true") == 0){
				PREDELIVERY=true;
			}
			
			
			logger.debug("Found following properties " + PREAGG_BATCH_SIZE +" " + " "+ PREAGGREGATOR_CACHE_NAME+ " "+ AGGREGATOR_CACHE_NAME);
			
			
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
