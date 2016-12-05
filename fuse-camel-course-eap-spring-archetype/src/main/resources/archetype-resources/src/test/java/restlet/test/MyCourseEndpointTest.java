package ${groupId}.restlet.test;

import java.io.File;

import javax.naming.InitialContext;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.ProxyBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import ${groupId}.restlet.MyServiceIntf;
import ${groupId}.restlet.MyResource;

@RunWith(Arquillian.class)
public class MyCourseEndpointTest {

    @Deployment
    public static JavaArchive createDeployment() {
        final JavaArchive  archive = ShrinkWrap.create(JavaArchive.class, "fuse-camel-restlet-eap-test.jar");
        archive.addPackage(MyServiceIntf.class.getPackage());
        archive.addAsResource(new File("src/main/webapp/META-INF/my-course-camel-context.xml"), "my-course-camel-context.xml");
        return archive;
    }
    
    @Test
    public void testMyCourseRoute() throws Exception {
        InitialContext context = new InitialContext();
        CamelContext camelContext = (CamelContext) context.lookup("java:jboss/camel/context/my-course-camel-context");
        Assert.assertNotNull("Expecting camel context to not be null", camelContext);

        MyServiceIntf myServiceProxy = new ProxyBuilder(camelContext)
		    .endpoint("direct:start")
			.binding(false)
			.build(MyServiceIntf.class);

        Response result = myServiceProxy.greet();
        Assert.assertEquals("Hi There!!!", result.getEntity());
        
        Response result2 = myServiceProxy.sayHello();
        MyResource resource = (MyResource) result2.getEntity();		
		Assert.assertEquals(16, resource.getAge());
        Assert.assertEquals("Hello Resource!", resource.getResource());
    }
}
