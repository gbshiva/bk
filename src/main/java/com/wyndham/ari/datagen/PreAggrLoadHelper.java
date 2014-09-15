package com.wyndham.ari.datagen;

import java.util.Calendar;
import java.util.Date;



public class PreAggrLoadHelper {

	
	
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
