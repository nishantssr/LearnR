package com.examples.camel.apibox;

import org.apache.camel.builder.RouteBuilder;

public class Proxy extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// Bridging / proxy

		from("jetty:http://0.0.0.0:8070/proxy/customer")
				.to("metrics:histogram:simple.histogram")
				.to("http://localhost:5000/customer?bridgeEndpoint=true&throwExceptionOnFailure=false");

	}

}
