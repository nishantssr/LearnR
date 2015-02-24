package com.examples.camel.logging;

import org.apache.camel.Exchange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanClass {
	private Log LOG = LogFactory.getLog(BeanClass.class);

	public void logging(Exchange exchange) {
		String body = exchange.getIn().getBody(String.class);
		LOG.info("Message body in BeanClass:  "+body);
	}
}
