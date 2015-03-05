package com.examples.camel.apibox;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;

public class ServiceMetrics {
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("jetty:http://0.0.0.0:8214/content")
						.to("metrics:timer:simple.timer?action=start")
						.to("http://localhost:5000/customer?bridgeEndpoint=true&throwExceptionOnFailure=false")
						.to("metrics:timer:simple.timer?action=stop");

			}

		});
		context.start();
	
		
	}
}
