package com.wyndham.ari.helper;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;

public class Instrumentation {
	static MetricRegistry registry = new MetricRegistry();
	

	static	final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
		
/**		
	static final CsvReporter reporter = CsvReporter.forRegistry(registry)
                .formatFor(Locale.US)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File("/tmp/data/"));
**/	
		
	
	public static MetricRegistry getRegistry(){
		return registry;
	}
	
	public static void start(){
		reporter.start(5, TimeUnit.SECONDS);
		
	}
	

}
