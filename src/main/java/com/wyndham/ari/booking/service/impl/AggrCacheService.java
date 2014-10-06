package com.wyndham.ari.booking.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.wyndham.ari.dao.Delivery;
import com.wyndham.ari.dao.DeliveryAdapter;
import com.wyndham.ari.dao.PreAgg;
import com.wyndham.ari.helper.CacheService;
import com.wyndham.ari.helper.Instrumentation;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

public class AggrCacheService implements Runnable {

	static Logger logger = Logger.getLogger(AggrCacheService.class);
	static final Timer timer = Instrumentation.getRegistry().timer(
			MetricRegistry.name(AggrCacheService.class, "AGGREGATORBATCH"));

	private List data = null;
	private int start = 0;
	private int end = 0;
	private Cache pCache = null;
	private Cache dCache = null;
	static Byte NEW = 1;
	static Byte INPROGRESS = 2;
	static Byte COMPLETED = 3;

	public AggrCacheService(List idata, int startIndex, int endIndex,
			Cache pCache, Cache dCache) {
		data = idata;
		start = startIndex;
		end = endIndex;
		this.pCache = pCache;
		this.dCache = dCache;
		logger.debug("Initializing CariCacheSerivce thread to laod data from index "
				+ startIndex + "to " + endIndex);
	}

	boolean pct = true;

	public void run() {
		// Timer.Context context = timer.time();
		for (int i = start; i < end; i++) {

			String key = (String) data.get(i);
			Element e = pCache.get(key);
			if (e != null) {
				PreAgg preagg = (PreAgg) e.getObjectValue();
				if (pct) {
					Delivery delivery = new DeliveryAdapter().convert(preagg);
					dCache.put(new Element(delivery.getReqId(), delivery));
					pct = false;
				} else {
					pct = true;
				}
				pCache.remove(key);
			}
		}
	}
	// context.stop();
}
