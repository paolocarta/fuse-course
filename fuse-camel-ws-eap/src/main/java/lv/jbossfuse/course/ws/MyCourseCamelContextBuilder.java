package lv.jbossfuse.course.ws;

import javax.inject.Inject;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ContextName("my-course-camel-context")
public class MyCourseCamelContextBuilder extends RouteBuilder {	
	
	@Inject
	private HelloWorldBean bean;
	
	@Override
	public void configure() throws Exception {
		from("direct:ws")
			.filter().simple("${in.body.length} > 5")				
				.bean(bean, "greet")
				.stop()
			.end()
			.bean(bean, "hellome");
	}
}
