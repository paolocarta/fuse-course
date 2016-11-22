package lv.jbossfuse.course.restlet;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.apache.camel.cdi.ContextName;

@Path("/hello")
public class MyHelloWorldService implements MyServiceIntf {

	//@Resource(lookup = "java:jboss/camel/context/my-spring-context")
	//private CamelContext camelContext;
	
	// uncomment to inject the Java DSL based Camel context using Camel CDI. 
	@Inject
	@ContextName("my-camel-context")
	private CamelContext camelContext;
	
	private MyServiceIntf myServiceProxy;
			
	@PostConstruct
	private void construct() {
		try {
			// camel proxy creation
			myServiceProxy = new ProxyBuilder(camelContext)
					.endpoint("direct:start")
					.binding(false)
					.build(MyServiceIntf.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@GET
	@Path("/greet")
	@Produces(MediaType.TEXT_PLAIN)	
	public Response greet() {
		return myServiceProxy.greet();
	}
	

	@GET
	@Path("/sayhello")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sayHello() {
		return myServiceProxy.sayHello();
	}
}
