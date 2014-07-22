package com.wyndham.ari.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.wyndham.ari.controller.CariASL;

public class CariProperties {
	
	static Logger logger = Logger.getLogger(CariProperties.class);
	public static int NUM_THREADS = 1;
	public static int BATCH_SIZE =10000;
	public static String CARI_DATA_SOURCE;
	public static String CARI_DATA_SOURCE_DELIM;
	public static String PREAGGREGATOR_CACHE_NAME;
	public static boolean STATS=false;
	
	
	
	
	public  CariProperties(String props){
		Properties cariprops = new Properties();
		BufferedReader bufferedTextIn  = null;
		try {
			String fileName = getClass().getClassLoader().getResource(props).getPath();
			logger.debug("Full path of property file"+ fileName);
			
			bufferedTextIn = new BufferedReader(new FileReader(fileName));
			if ( bufferedTextIn == null ){
				logger.error("Unable to read property file "+ fileName);
				System.exit(1);
			}
			cariprops.load(bufferedTextIn);
			NUM_THREADS= Integer.parseInt(cariprops.getProperty("NUM_THREADS"));
			BATCH_SIZE=Integer.parseInt(cariprops.getProperty("BATCH_SIZE"));
			CARI_DATA_SOURCE=cariprops.getProperty("CARI_DATA_SOURCE");
			CARI_DATA_SOURCE_DELIM=cariprops.getProperty("CARI_DATA_SOURCE_DELIM");
			PREAGGREGATOR_CACHE_NAME=cariprops.getProperty("PREAGGREGATOR_CACHE_NAME");
			if (cariprops.getProperty("STATS","false").compareToIgnoreCase("true") == 0){
				STATS=true;
			}
			logger.debug("Found following properties " + NUM_THREADS +" "+BATCH_SIZE +" " + CARI_DATA_SOURCE +" " +CARI_DATA_SOURCE_DELIM +" "+ PREAGGREGATOR_CACHE_NAME);
			
			
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
