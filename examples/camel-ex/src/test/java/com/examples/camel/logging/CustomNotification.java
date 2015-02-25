package com.examples.camel.logging;

import java.util.Date;
import java.util.EventObject;

import org.apache.camel.Exchange;
import org.apache.camel.management.event.ExchangeCompletedEvent;
import org.apache.camel.management.event.ExchangeSendingEvent;
import org.apache.camel.management.event.ExchangeSentEvent;
import org.apache.camel.support.EventNotifierSupport;

public class CustomNotification extends EventNotifierSupport {

	@Override
	public void notify(EventObject event) throws Exception {
		// TODO Auto-generated method stub
		if (event instanceof ExchangeSentEvent) {
			ExchangeSentEvent sent = (ExchangeSentEvent) event;
			log.info(">>>>   Took " + sent.getTimeTaken()+ " milli sec");

		}
		if (event instanceof ExchangeCompletedEvent) {
			ExchangeCompletedEvent exchangeCompletedEvent = (ExchangeCompletedEvent) event;
			Exchange exchange = exchangeCompletedEvent.getExchange();
			String routeId = exchange.getFromRouteId();
			Date created = ((ExchangeCompletedEvent) event).getExchange()
					.getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
			// calculate elapsed time
			Date now = new Date();
			long elapsed = now.getTime() - created.getTime();
			log.info(">>>   Took " + elapsed
					+ " millis for the exchange on the route : " + routeId);

		}
		if (event instanceof ExchangeSendingEvent) {
			ExchangeSendingEvent sending = (ExchangeSendingEvent) event;
			log.info("SendingEndPoint  " + sending.getEndpoint());
			Exchange exchange = sending.getExchange();
			String routeId = exchange.getFromRouteId();
			Date created = ((ExchangeSendingEvent) event).getExchange()
					.getProperty(Exchange.CREATED_TIMESTAMP, Date.class);
			Date now = new Date();
			long elapsed = now.getTime() - created.getTime();
			log.info(">>> Took " + elapsed+" milli sec");

		}

	}

	@Override
	public boolean isEnabled(EventObject event) {
		return true;
	}

}
