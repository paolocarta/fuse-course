package lv.jbossfuse.course.restlet;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.bean.BeanInvocation;

@ContextName("my-camel-context")
public class MyCamelContextBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:start")
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					
					BeanInvocation beanInvocation = exchange.getIn().getBody(BeanInvocation.class);					
			        String methodName = beanInvocation.getMethod().getName();

			        log.info("Processing CamelProxy route... method: " + methodName);
			        
			        if ("greet".equals(methodName)) {			            
			            exchange.getOut().setBody(Response.ok("Hi There!!!").build());
			        } else if("sayHello".equals(methodName)) {
			            // how to get the arguments if needed:
			        	// Customer updatedCustomer = (Customer) beanInvocation.getArgs()[0];
			        	MyResource myresource = new MyResource();
			        	myresource.setAge(16);
			        	myresource.setResource("Hello Resource!");
			            exchange.getOut().setBody(Response.ok(myresource).build());
			        }
				}
			});
	}
}
