package com.examples.camel.apibox;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;

public class FileTransfer {
	public static void main(String[] args) throws Exception {

		CamelContext context = new DefaultCamelContext();
		context.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("file:data/inbox?noop=true").to(
						"metrics:counter:simple.counter")
						.to("file:data/outbox");
			}

		});
		context.start();
		Thread.sleep(210000);
	}
}
