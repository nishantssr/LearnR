package com.examples.camel.apibox;

import org.apache.camel.builder.RouteBuilder;

import com.codahale.metrics.Counter;

public class ContentBased extends RouteBuilder {
	public static Object value;
	public static Counter count = new Counter();

	@Override
	public void configure() throws Exception {

		from("jetty:http://0.0.0.0:8040/content")
				.to("metrics:timer:simple.timer?action=start")

				.choice()
				.when(header("service").isEqualTo("customer"))
				.to("http://localhost:5000/customer?bridgeEndpoint=true&throwExceptionOnFailure=false")
				.to("metrics:timer:simple.timer?action=stop")
				.otherwise()
				.to("http://localhost:5000/order?bridgeEndpoint=true&throwExceptionOnFailure=false")
				.to("metrics:timer:simple.timer?action=stop");

	}
}
