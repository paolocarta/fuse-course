package lv.jbossfuse.course.ws;

import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;

@WebService(name = "MyCourseEndpointService", 
	endpointInterface = "lv.jbossfuse.course.ws.MyCourseEndpoint",
	serviceName = "MyCourseEndpointService", portName = "MyCourseEndpointPort")		 
public class MyCourseEndpointImpl implements MyCourseEndpoint {

	@Inject
	@ContextName("my-course-camel-context")
	private ProducerTemplate producer;
	
	@Inject
	@Uri("direct:ws")
	private Endpoint ws;	
	
	@Override
	public String getHelloWorldAsString(String name) {		   
		return producer.requestBody(ws, name, String.class);
	}
}
