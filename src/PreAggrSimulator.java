package com.whg.cari.push;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.wyndham.ari.helper.Instrumentation;

/**
 * Servlet implementation class PreAggrSimulator
 */
public class PreAggrSimulator extends HttpServlet {
	static Logger logger = Logger.getLogger(PreAggrSimulator.class);
	private static final boolean dtoLog = true;
	private static final long serialVersionUID = 1L;

	
	public void init() throws ServletException {
		Instrumentation.start();
	}
	
    /**
     * Default constructor. 
     */
    public PreAggrSimulator() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		long startTime = System.currentTimeMillis();
		
		LoadContext context = PreAggrLoadHelper.getLoadConext(request);
		PreAggrService preAggrSer = new PreAggrService(context);
		preAggrSer.process();

		long endTime = System.currentTimeMillis();
		/*Cache cache = CacheService.getCache(PreAggrService.PREAGGREGATOR_CACHE_NAME);
		StatisticsGateway stats = cache.getStatistics();
		
		StringBuilder builder = new StringBuilder();
		builder.append("<response>");
		builder.append("<CacheTotalsize>"+stats.getSize()+"</CacheTotalsize>");
		builder.append("<LocalHeapCount>").append(stats.getLocalHeapSize()).append("</LocalHeapCount>");
		builder.append("<OffHeapCount>").append(stats.getLocalOffHeapSize()).append("</OffHeapCount>");
		builder.append("<LocalHeapSizeInBytes>").append(stats.getLocalHeapSizeInBytes()).append("</LocalHeapSizeInBytes>");
		builder.append("<OffHeapSizeInBytes>").append(stats.getLocalOffHeapSizeInBytes()).append("</OffHeapSizeInBytes>");
		builder.append("<RemoteCount>").append(stats.getRemoteSize()).append("</RemoteCount>");
		builder.append("</response>");*/
		
		response.setContentType("text/xml");
		//response.getOutputStream().write(builder.toString().getBytes());
		
		StringBuilder builder = new StringBuilder();
		builder.append("<response>");
		
		int i=1;
		for(String threadTime:context.getResponseTimes()){
			builder.append("<Thread_Resp_Time_"+i+">").append(threadTime).append("</Thread_Resp_Time_"+i+">");
			i++;
		}
		
		builder.append("<totalTime>").append(String.valueOf(endTime-startTime)).append("</totalTime>");
		builder.append("</response>");
		
		response.getOutputStream().write(builder.toString().getBytes());
	}

	
}
