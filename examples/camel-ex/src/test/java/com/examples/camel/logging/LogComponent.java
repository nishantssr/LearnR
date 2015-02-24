package com.examples.camel.logging;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class LogComponent {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				from("http://127.0.0.1:5000/order").to("log:input").process(
						new Processor() {

							@Override
							public void process(Exchange exchange)
									throws Exception {
								// TODO Auto-generated method stub
								System.out
										.println("Message body in process method: "
												+ exchange.getIn().getBody(
														String.class));
							}
						});
			}
		});
		context.start();
		Thread.sleep(1500);
	}

}
