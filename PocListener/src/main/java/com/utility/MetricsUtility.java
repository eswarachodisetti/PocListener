package com.utility;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.StandardExports;

public class MetricsUtility {

	public void exposeMetrics()
	{
		try {
			CollectorRegistry.defaultRegistry.register(new StandardExports());
			CollectorRegistry.defaultRegistry.register(new MemoryPoolsExports());
			CollectorRegistry.defaultRegistry.register(new GarbageCollectorExports());
			
			Server server=new Server(8081);
			ServletContextHandler context=new ServletContextHandler();
			context.setContextPath("/");
			server.setHandler(context);
			context.addServlet(new ServletHolder(new MetricsServlet()), "/listenerMetrics");
			
			server.start();
			//server.join();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
