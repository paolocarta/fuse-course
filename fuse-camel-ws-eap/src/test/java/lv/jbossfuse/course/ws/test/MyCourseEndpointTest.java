package lv.jbossfuse.course.ws.test;

import java.io.File;

import javax.inject.Inject;

import org.apache.camel.Endpoint;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.cdi.Uri;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import lv.jbossfuse.course.ws.MyCourseCamelContextBuilder;

@RunWith(Arquillian.class)
public class MyCourseEndpointTest {

    @Deployment
    public static JavaArchive createDeployment() {
        final JavaArchive  archive = ShrinkWrap.create(JavaArchive.class, "fuse-camel-ws-eap-tests.jar");
        archive.addPackage(MyCourseCamelContextBuilder.class.getPackage());
        archive.addAsResource(new File("src/main/webapp/WEB-INF/beans.xml"), "META-INF/beans.xml");
        return archive;
    }
    
    @Inject
    @ContextName("my-course-camel-context")
    private ProducerTemplate producer;

    @Inject
	@Uri("direct:ws")
	private Endpoint ws;	
    
    @Test
    public void testMyCourseRoute() {
        Assert.assertNotNull("Expecting producer bound to camel-context to not be null", producer);

        String result = producer.requestBody(ws, "Pavel", String.class);
        Assert.assertEquals("Hello Pavel!", result);
        
        String result2 = producer.requestBody(ws, "Evgeni", String.class);
        Assert.assertEquals("Greetings Evgeni!", result2);
    }
}
