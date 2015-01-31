package com.examples.camel;

import org.apache.camel.Exchange;

public class Aggregation implements
		org.apache.camel.processor.aggregate.AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange message, Exchange resource) {
		// TODO Auto-generated method stub
		String old = resource.getIn().getBody(String.class);
		System.out.println("OLD:: \n" + old);
		String newMsg = message.getIn().getBody(String.class);
		System.out.println("NEW:: \n" + newMsg);
		System.out.println("MERGED::" + old + newMsg);
		message.getIn().setBody(old + newMsg);
		return message;
	}

}
