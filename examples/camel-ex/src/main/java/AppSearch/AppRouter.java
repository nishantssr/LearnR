package AppSearch;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
				.to("metrics:timer:simple.timer?action=start")
				.process(new Transformation())
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						// TODO Auto-generated method stub
						System.out.println(exchange.getIn().getBody(
								String.class));
					}
				})
				.to("http://scoring-i-96b9ba5c.us-west-1c.stage.quixey.com:8778/score?bridgeEndpoint=true&throwExceptionOnFailure=false")
				.setHeader(Exchange.HTTP_METHOD, constant("POST"))
				.to("metrics:timer:simple.timer?action=stop")
				.process(new ScoringTransform());
	}

}
