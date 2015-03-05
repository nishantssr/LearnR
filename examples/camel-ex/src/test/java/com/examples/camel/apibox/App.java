package com.examples.camel.apibox;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		final CamelContext cc = new DefaultCamelContext();
		MetricsRoutePolicyFactory mrpf = new MetricsRoutePolicyFactory();
		mrpf.setUseJmx(true);

		cc.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
		// Add routes
		cc.addRoutes(new Proxy());
		cc.addRoutes(new ContentBased());

		cc.start();

		// Handle shutdown
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					cc.stop();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});

		waitForStop();
		cc.start();

	}

	static void waitForStop() {
		while (true) {
			try {
				Thread.sleep(2540);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
