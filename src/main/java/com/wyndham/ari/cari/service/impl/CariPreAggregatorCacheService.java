package com.wyndham.ari.cari.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.Instrumentation;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;


public class CariPreAggregatorCacheService implements Runnable{
	
	static Logger logger = Logger.getLogger(CariPreAggregatorCacheService.class);
	static final Timer timer = Instrumentation.getRegistry().timer(MetricRegistry.name(CariPreAggregatorCacheService.class, "cachebatchload"));

	private List data=null;
	private int start=0;
	private int end=0;
	private String caricache;
	
	public  CariPreAggregatorCacheService(List idata, int startIndex, int endIndex,String cache){
		data = idata;
		start = startIndex;
		end = endIndex;
		caricache = cache;
		logger.debug("Initializing CariCacheSerivce thread to laod data from index "+ startIndex + "to "+ endIndex);
	}

	public void run() {
		Cache cache = CacheService.getCache(caricache);
		final Timer.Context context = timer.time();

		for (int i=start; i < end ; i++){
			PreAgg aggr = (PreAgg)data.get(i);
			Element e = new Element(aggr.getKey(),aggr);
			cache.put(e);
			
		}
		context.stop();
		
		
	}
	
	
	
	
}
