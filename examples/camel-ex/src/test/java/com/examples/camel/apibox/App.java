package com.examples.camel.apibox;

import io.hawt.embedded.Main;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		final CamelContext cc = new DefaultCamelContext();

		// Add routes
		cc.addRoutes(new Proxy());
		// cc.addRoutes(new ContentBased());
		Main main = new Main();
		main.setWar("/home/uttam/Downloads/hawt"); // download and specify the
														// path of this link
														// ->https://oss.sonatype.org/content/repositories/public/io/hawt/sample/1.4.45/sample-1.4.45.war
		main.run();
		cc.start();
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

	}

	static void waitForStop() {
		while (true) {
			try {
				Thread.sleep(Long.MAX_VALUE);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

}
