package com.examples.camel.logging;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class NotificationLogging {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		context.getManagementStrategy().addEventNotifier(
				new CustomNotification());
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("jetty:http://127.0.0.1:2000/order1").routeId("Order1")
						.to("http://127.0.0.1:5000/order?bridgeEndpoint=true&throwExceptionOnFailure=false")
						.process(new Processor() {

							@Override
							public void process(Exchange exchange)
									throws Exception {
								// TODO Auto-generated method stub
								String body = exchange.getIn().getBody(String.class);
								body  = body +"extra text added along with body";
								exchange.getOut().setBody(body);

							}
						});
			}
		});
		context.start();
		Thread.sleep(1500);
	}

}
