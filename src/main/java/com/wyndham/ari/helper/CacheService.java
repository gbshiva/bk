package com.wyndham.ari.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.StatisticsGateway;

public class CacheService {
		static Logger logger = Logger.getLogger(CacheService.class);
	  private static CacheManager cmgr = new CacheManager();
	  

	  public static CacheManager getCacheManager() {
	    return cmgr;
	  }

	  public static void shutDown(){
		  if ( cmgr != null )
				  cmgr.shutdown();
	  }
	  
	  public static Cache getBulkCache(String name) {
		    Ehcache cache = cmgr.getCache(name);
		    if (cache != null){
		    	cache.setNodeBulkLoadEnabled(true);
		    }
		    return (Cache)cache;
		  }
	  
	  
	  
	  public static Cache getCache(String name) {
	    Ehcache cache = cmgr.getCache(name);
	    return (Cache)cache;
	  }

	  public Map getAll(Cache cache)
	  {
	    List keys = cache.getKeys();
	    Map val = cache.getAll(keys);
	    return val;
	  }

	  public void shutdown() {
	    cmgr.shutdown();
	  }

	  public void getAll(Cache cache, int getThreads) {
	    try {
	      List keys = cache.getKeys();
	      int numElements = keys.size();
	      System.out.println(cache.getName() + " Elements = " + numElements + " Spawning " + getThreads);
	      int eachThreadCount = numElements / getThreads;
	      ExecutorService executor = Executors.newFixedThreadPool(getThreads);
	      for (int i = 0; i < getThreads; i++) {
	        List sublist = sublist(keys, i * eachThreadCount, (i + 1) * eachThreadCount);
	        Runnable worker = new Cacheget(cache, sublist);
	        executor.execute(worker);
	      }
	      executor.shutdown();
	      while (!executor.isTerminated())
	      {
	        Thread.sleep(50L);
	      }
	    }
	    catch (Exception ex) {
	      System.out.println(ex);
	    }
	  }

	  public static List sublist(List s, int strindex, int endindex)
	  {
	    ArrayList sub = new ArrayList();
	    for (int i = 0; i < endindex - strindex; i++) {
	      sub.add(s.get(strindex + i));
	    }

	    return sub;
	  }

	  public void printStats(boolean stats_size)
	  {
	    String[] name = cmgr.getCacheNames();
	    for (int i = 0; i < name.length; i++) {
	      Cache cache = cmgr.getCache(name[i]);

	      StatisticsGateway stats = cache.getStatistics();
	      System.out.print("Stats,Time," + new Date().getTime() + ",CacheName," + name[i] + ",Totalsize," + stats.getSize() + ",LocalHeap Count," + stats.getLocalHeapSize() + ",OffHeap Count," + stats.getLocalOffHeapSize());

	      if (stats_size) System.out.print(",LocalHeapSize," + stats.getLocalHeapSizeInBytes() + ",OffHeapSize," + stats.getLocalOffHeapSizeInBytes());
	    }
	    System.out.println();
	  }

	  public class Cacheget
	    implements Runnable
	  {
	    private List keys;
	    private Cache cache;

	    public Cacheget(Cache cache, List keys)
	    {
	      this.cache = cache;
	      this.keys = keys;
	    }

	    public void run() {
	      for (int i = 0; i < this.keys.size(); i++) {
	        Element e = this.cache.get(this.keys.get(i));
	        
	      }
	    }
	  }
	}