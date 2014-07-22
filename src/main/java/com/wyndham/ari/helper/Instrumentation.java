package com.wyndham.ari.helper;

import java.io.File;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.CsvReporter;
import com.codahale.metrics.MetricRegistry;

public class Instrumentation {
	static MetricRegistry registry = new MetricRegistry();
	static{

		final ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
		
/**		
		final CsvReporter reporter = CsvReporter.forRegistry(registry)
                .formatFor(Locale.US)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build(new File("/tmp/data/"));
**/	
		reporter.start(1, TimeUnit.SECONDS);
	}
	
	public static MetricRegistry getRegistry(){
		return registry;
	}

}
