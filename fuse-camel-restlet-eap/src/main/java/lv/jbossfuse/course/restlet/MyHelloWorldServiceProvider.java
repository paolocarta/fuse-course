package lv.jbossfuse.course.restlet;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class MyHelloWorldServiceProvider implements MyServiceIntf {

	@Override
	public Response greet() {
		return Response.status(Status.OK)
				.entity("Hi There!!!")
				.build();
	}

	@Override
	public Response sayHello() {
		MyResource resource = new MyResource();
		resource.setAge(16);
		resource.setResource("Hello Resource!");
		return Response.status(Status.OK)
				.entity(resource)
				.build();
	}
}
