package com.wyndham.ari.cari.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.datagen.LoadContext;
import com.wyndham.ari.datagen.PreAggrLoadGenerator;
import com.wyndham.ari.helper.CariProperties;
import com.wyndham.ari.helper.Instrumentation;
import com.wyndham.ari.service.iCariPreAggregatorService;

public class CariPreAggregatorService implements iCariPreAggregatorService {
	static Logger logger = Logger.getLogger(CariPreAggregatorService.class);
	static final Timer timer = Instrumentation.getRegistry().timer(MetricRegistry.name(CariPreAggregatorService.class, "CARITOTALLOADTIME"));
	static final Timer dataReadTimer = Instrumentation.getRegistry().timer(MetricRegistry.name(CariPreAggregatorService.class, "CARIDATAGENTIME"));
	public void load(CariProperties prop) {
		//Retrieve the Data file and start loading data into cache. 
		
		try {
			logger.info("Reading CARI data from file "+prop.CARI_DATA_SOURCE);
			
			/**
			BufferedReader in = new BufferedReader(new FileReader( (new File(ClassLoader.getSystemResource(prop.CARI_DATA_SOURCE).toURI()))));
			if (in == null){
				logger.error("Unable to read CARI Data File. "+ prop.CARI_DATA_SOURCE);
				System.exit(1);
			}
			
			
			
			
			String line = null;
			ArrayList<PreAgg> dataList = new ArrayList();
			while ((line = in.readLine()) != null) {
				String fields[] = line.split(prop.CARI_DATA_SOURCE_DELIM);
				
				PreAgg agg = new PreAgg(fields[0], fields[1], fields[2], fields[3], new Date(), Byte.valueOf(fields[5]), Byte.valueOf(fields[6]));
				dataList.add(agg);
		    }			
			filecontext.stop();
			logger.info("Completed Reading CARI data from file "+prop.CARI_DATA_SOURCE);
			
			**/
			
			Timer.Context context2 = dataReadTimer.time();
			LoadContext context = new LoadContext();
			context.setBrandId(prop.BRANDID);
			
			context.setPropertyId(prop.PROPERTYID);
			context.setRatePlanCnt(prop.RATEPLANCNT);
			context.setRoomTypeCnt(prop.ROOMTYPECNT);
			context.setInventoryDays(prop.INVENTORYDAYS);
			context.setMsgTypes(prop.MSGTYPES);
			context.setThreadCnt(prop.THREADCNT); 

			PreAggrLoadGenerator loadGenerator = new PreAggrLoadGenerator(context);
			List<PreAgg> preAggrLst = loadGenerator.constructPreAggrList();
			List<PreAgg> dataList = loadGenerator
					.fillAvailabilityData(preAggrLst);	
			
			context2.stop();
			
			
			
			
			Timer.Context context1 = timer.time();
			int totalThreads = dataList.size()/prop.BATCH_SIZE;
			logger.info("Staring  "+totalThreads +" threads to load data of size "+ dataList.size());
			ExecutorService executor = Executors.newFixedThreadPool(prop.NUM_THREADS);
			for (int i = 0 ; i< totalThreads ; i++){
				Runnable worker = new CariPreAggregatorCacheService(dataList, i*prop.BATCH_SIZE, (i+1)*prop.BATCH_SIZE, prop.PREAGGREGATOR_CACHE_NAME);
				executor.execute(worker);
			}
			executor.shutdown();
		      while (!executor.isTerminated())
		      {
		        Thread.sleep(50L);
		      }
		      context1.stop();
		}catch(Exception ex){
			logger.error(ex);
		}

	}
	

}
