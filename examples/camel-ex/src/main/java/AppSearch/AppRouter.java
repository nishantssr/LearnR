package AppSearch;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.metrics.MetricsConstants;

public class AppRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		from(
				"http://search-be.stage.quixey.com:8080/app_search?q=hello&bridgeEndpoint=true&throwExceptionOnFailure=false")
				.setHeader(MetricsConstants.HEADER_HISTOGRAM_VALUE,
						constant(992L))
				.to("metrics:histogram:simple.histogram?value=700")
				.process(new Transformation())

				.to("http://scoring-i-96b9ba5c.us-west-1c.stage.quixey.com:8778/score")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"));
		// .process(new ScoringTransform());
	}
}
