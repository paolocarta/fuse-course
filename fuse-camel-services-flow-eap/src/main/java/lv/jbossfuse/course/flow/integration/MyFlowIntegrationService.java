package lv.jbossfuse.course.flow.integration;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.util.CamelContextHelper;

@Path("/")
public class MyFlowIntegrationService {
	
	private static final String START_ENDPOINT_URI = "direct:start";

	@Resource(lookup = "java:jboss/camel/context/my-flow-spring-context")
	private CamelContext camelContext;
	
	@GET
	@Path("/monthlyreceipt/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMonthlyReceipt(@PathParam("userId") String userId) {
		ProducerTemplate producer = camelContext.createProducerTemplate();
		Exchange exchange = CamelContextHelper.getMandatoryEndpoint(camelContext, START_ENDPOINT_URI)
				.createExchange(ExchangePattern.InOut);
		exchange.getIn().setBody(userId);
		Exchange out = producer.send(START_ENDPOINT_URI, exchange);
		if (out.isFailed()) {			
			return Response.status(Status.NOT_FOUND)
					.entity(out.getOut().getBody(String.class))
					.build();
		} else {
			return Response.ok()
					.entity(out.getOut().getBody(String.class))
					.build();
		}
	}
}
