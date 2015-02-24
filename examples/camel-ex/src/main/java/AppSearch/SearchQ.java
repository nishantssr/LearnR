package AppSearch;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.impl.DefaultCamelContext;

public class SearchQ {

	public static void main(String[] args) throws Exception {
		CamelContext cont = new DefaultCamelContext();
		cont.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
		cont.addRoutes(new AppRouter());
		// cont.addRoutes(new QupRouter());
		// cont.addRoutes(new RbscRouter());
		// cont.addRoutes(new InsideSearchRoute());
		cont.start();
		Thread.sleep(210000);

	}

}