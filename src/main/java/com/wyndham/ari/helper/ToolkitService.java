package com.wyndham.ari.helper;

import org.apache.log4j.Logger;
import org.terracotta.toolkit.*;
import org.terracotta.toolkit.collections.ToolkitBlockingQueue;

import java.util.Queue;


public class ToolkitService {
	static Logger logger = Logger.getLogger(ToolkitService.class);
	private Toolkit toolkit;
	private static ToolkitService instance = null;

	protected ToolkitService(String uri) throws Exception {
		toolkit = ToolkitFactory
				.createToolkit(uri);

	}

	public  static ToolkitService getInstance(String uri) {
		if (instance == null) {
			synchronized(logger){
				if (instance == null){
			try{
			instance = new ToolkitService(uri);
			}catch(Exception ex){
				logger.error(ex);
			}
				}
			}
		}

		
		return instance;
	}
	
	public Queue getQueue(String name){
		ToolkitBlockingQueue queue1 = 
			    toolkit.getBlockingQueue(name, null);
		return queue1;
	}
	
	
	
}
