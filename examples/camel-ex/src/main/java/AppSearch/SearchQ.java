package AppSearch;

import io.hawt.embedded.Main;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class SearchQ {
	public static void main(String[] args) throws Exception {
		CamelContext cont = new DefaultCamelContext();
		cont.addRoutes(new AppRouter());
		Main main = new Main();
		main.setWar("/home/uttam/Downloads/hawtio");   //refer http://hawt.io/getstarted/
		main.run();
		cont.start();
		Thread.sleep(210000);
		// cont.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
		// cont.addRoutes(new QupRouter());
		// cont.addRoutes(new RbscRouter());
		// cont.addRoutes(new InsideSearchRoute());
		
	}
}