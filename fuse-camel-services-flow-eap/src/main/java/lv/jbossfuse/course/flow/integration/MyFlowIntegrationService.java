package lv.jbossfuse.course.flow.integration;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;

@Path("/")
public class MyFlowIntegrationService {

	@Resource(lookup = "java:jboss/camel/context/my-flow-spring-context")
	private CamelContext camelContext;
	
	@GET
	@Path("/monthlyreceipt/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMonthlyReceipt(@PathParam("userId") String userId) {
		ProducerTemplate producer = camelContext.createProducerTemplate();
        String result = producer.requestBody("direct:start", userId, String.class);
        return result;
	}
}
