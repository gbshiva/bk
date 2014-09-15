package com.whg.cari.push;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

import org.apache.log4j.Logger;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.cari.service.impl.CariPreAggregatorCacheService;
import com.wyndham.ari.dao.PreAggrDTO;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.Instrumentation;

public class PreAggrService {

	public static final String PREAGGREGATOR_CACHE_NAME="preaggregator";
	
	static final Timer timer = Instrumentation.getRegistry().timer(MetricRegistry.name(PreAggrService.class, "cachetoalload"));
	
	static Logger logger = Logger.getLogger(PreAggrService.class);
	
	private LoadContext context;

	public PreAggrService(LoadContext context) {
		this.context = context;
	}

	public void process() {
		try{
		
			final Timer.Context timeContext = timer.time();
			
			logger.debug("building pre aggr objects");
					
			// generate load
			PreAggrLoadGenerator loadGenerator = new PreAggrLoadGenerator(context);
			List<PreAggrDTO> preAggrLst = loadGenerator.constructPreAggrList();
			List<PreAggrDTO> preAggrLstFinal = loadGenerator
					.fillAvailabilityData(preAggrLst);
	
	
			System.out.println("total objects in the list :" + preAggrLstFinal.size());
			logger.debug("total objects in the list :" + preAggrLstFinal.size());
		
			//load data
			loadData(preAggrLstFinal); 
			
			timeContext.stop();
		}catch(Exception e){
			logger.error(e);
		}
		
	}
	
	private void loadData(List<PreAggrDTO> preAggrLst)throws Exception{
		
		int threadCnt=1;
		if (context.getThreadCnt() != null) {
			threadCnt = Integer.parseInt(context.getThreadCnt());
		}
		if(preAggrLst.size() <= threadCnt){
			threadCnt = preAggrLst.size();
		}

		int batchSize = preAggrLst.size() / threadCnt;

		logger.info("Staring  " + threadCnt
				+ " threads to load data of size " + preAggrLst.size());
		
		ExecutorService executor = Executors
				.newFixedThreadPool(threadCnt);
		
		List<CariPreAggregatorCacheService> workerLst = new ArrayList<CariPreAggregatorCacheService>();
		for (int i = 0; i < threadCnt; i++) {
			
			int start = i* batchSize;
			int end = (i + 1) * batchSize;
			if(i!=0){
				start = i* batchSize+1;
			}
			if(i==threadCnt-1){
				end = preAggrLst.size()-1;
			}
					
			CariPreAggregatorCacheService worker = new CariPreAggregatorCacheService(preAggrLst, start, end,
					PREAGGREGATOR_CACHE_NAME);
			workerLst.add(worker);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			Thread.sleep(50L);
		}	
		
		List<String> responseTimes = new ArrayList<String>();
		for(CariPreAggregatorCacheService worker:workerLst){
			responseTimes.add(String.valueOf(worker.getTotalTime()));
		}
		context.setResponseTimes(responseTimes);
	}
	
	public static void main(String a[]){
		LoadContext context = new LoadContext();
		context.setBrandId("DI");
		context.setPropertyId("12345");
		context.setRatePlanCnt("5");
		context.setRoomTypeCnt("5");
		context.setInventoryDays("365");
		context.setMsgTypes("S,I,R");
		context.setThreadCnt("5"); 
		
		PreAggrService preAggrSer = new PreAggrService(context);
		preAggrSer.process();
		
		Cache cache = CacheService.getCache(PreAggrService.PREAGGREGATOR_CACHE_NAME);
		StatisticsGateway stats = cache.getStatistics();
		
		for (Object key: cache.getKeys()) {
		    Element element = cache.get(key);
		    PreAggrDTO agrDTO = (PreAggrDTO)element.getObjectValue();
		    
		    System.out.println(agrDTO);
	        //System.out.println("bytes:"+ MemoryMeasurer.measureBytes(agrDTO));
		}

		System.out.println("cache size "+stats.getSize());
		
	}
}
