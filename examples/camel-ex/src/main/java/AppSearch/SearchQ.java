package AppSearch;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;



public class SearchQ {

	public static void main(String[] args) throws Exception {
		CamelContext cont = new DefaultCamelContext();
		MetricsRoutePolicyFactory mrpf = new MetricsRoutePolicyFactory();
		mrpf.setUseJmx(true);
		cont.addRoutePolicyFactory(mrpf);
		cont.addRoutes(new AppRouter());

		cont.start();
		// Handle shutdown
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					cont.stop();
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