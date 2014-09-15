package com.whg.cari.push;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class PreAggrLoadHelper {

	public static LoadContext getLoadConext(HttpServletRequest request){
		
		LoadContext context = new LoadContext();
		context.setBrandId(request.getParameter("BRAND_ID"));
		context.setPropertyId(request.getParameter("PROPERTY_ID"));
		context.setRatePlanCnt(request.getParameter("RATE_PLAN_CNT"));
		context.setRoomTypeCnt(request.getParameter("ROOM_TYPE_CNT"));
		context.setInventoryDays(request.getParameter("INVENTORY_DAYS"));
		context.setBatchCnt(request.getParameter("BATCH_CNT"));
		context.setMsgTypes(request.getParameter("MSG_TYPES"));
		context.setThreadCnt(request.getParameter("THREAD_CNT"));
		
		return context;
	}
	
	public static Date getDateByAddingDays(Integer days) {
		
		//current day starting 12 AM
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);
		
		//now add # of days
		cal.add(Calendar.DAY_OF_MONTH, days);
		
		//return new date
		Date date = cal.getTime();
		return date;
	}
}
