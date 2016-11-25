package lv.jbossfuse.course.ws;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ContextName("my-course-camel-context")
public class MyCourseCamelContextBuilder extends RouteBuilder {	
	
	@Override
	public void configure() throws Exception {
		from("direct:ws")
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getOut().setBody("Hi " + exchange.getIn().getBody(String.class));			        
				}
			});
	}
}
