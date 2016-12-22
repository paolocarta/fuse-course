package lv.jbossfuse.course.flow.test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import lv.jbossfuse.course.flow.integration.MyFlowIntegrationService;
import lv.jbossfuse.course.flow.integration.converter.StringToListCurrentMonthReceiptsConverter;
import lv.jbossfuse.course.flow.services.RestActivator;
import lv.jbossfuse.course.flow.services.monthlyreceipt.SibNskMonthlyReceiptServiceImpl;
import lv.jbossfuse.course.flow.services.monthlyreceipt.model.MonthlyReceipt;
import lv.jbossfuse.course.flow.services.phonebook.PhoneBookService;
import lv.jbossfuse.course.monthlyreceiptservice.SibNskMonthlyReceiptService;

@RunWith(Arquillian.class)
public class MyFlowCamelContextTest {
	
	private static final String TEST_USER_ID = "ki10105";
	
	private static final String INVALID_USER_ID = "ki10106";

	private static final String RECORD_NOT_FOUND_ERROR_RESPONSE = "The phone record '" + INVALID_USER_ID 
			+ "' is not found";
	
	private static final String VALID_JSON_RESPONSE = "{\"month-code\":\"04-2016\",\"service-receipts\":"
			+ "[{\"@service\":\"0001\",\"#text\":\"10.01\"},{\"@service\":\"0002\",\"#text\":\"15.2\"},"
			+ "{\"@service\":\"003\",\"#text\":\"-5.2\"}],\"user\":\"ki10105\"}";
	
	private static final String MY_FLOW_INTEGRATION_SERVICE_ENDPOINT_PROPERTY = "endpoint.myflowintegrationservice";
	
	private static final Properties properties = new Properties();
	
    @Deployment
    public static WebArchive createDeployment() {
    	// see http://stackoverflow.com/questions/13001371/adding-all-maven-dependencies-to-arquillian
    	// how to deploy a test with dependencies
        final WebArchive  archive = ShrinkWrap.create(WebArchive.class, "fuse-camel-tests.war");
        File[] libraries = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();
        archive.addAsLibraries(libraries);        
        archive.addPackage(MyFlowIntegrationService.class.getPackage());
        archive.addPackage(StringToListCurrentMonthReceiptsConverter.class.getPackage());
        archive.addPackage(RestActivator.class.getPackage());
        archive.addPackage(PhoneBookService.class.getPackage());
        archive.addPackage(SibNskMonthlyReceiptServiceImpl.class.getPackage());
        archive.addPackage(MonthlyReceipt.class.getPackage());
        archive.addPackage(SibNskMonthlyReceiptService.class.getPackage());
        archive.addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"));
        archive.addAsManifestResource(new File("src/main/webapp/META-INF/jboss-camel-context.xml"));            
        archive.addAsResource(new File("src/main/resources/META-INF/services/org/apache/camel/TypeConverter"),
        		"META-INF/services/org/apache/camel/TypeConverter");
        archive.addAsResource(new File("src/test/resources/test-endpoints.properties"), "endpoints.properties");        
        archive.addAsResource(new File(
        		"target/generated-sources/main/java/lv/jbossfuse/course/monthlyreceiptservice/MonthlyReceiptService.wsdl"), 
        		"lv/jbossfuse/course/monthlyreceiptservice/MonthlyReceiptService.wsdl");
        
        // Show the deploy structure
        System.out.println(archive.toString(true));
        
        return archive;
    }
    
    @BeforeClass
    public static void loadProperties() throws IOException {    
    	try (BufferedInputStream bis = new BufferedInputStream(
    			MyFlowCamelContextTest.class.getResourceAsStream("/test-endpoints.properties"))) {
    		properties.load(bis);
    	}    	
    }

    @Test
    public void testMyFlowCamelContext() throws NamingException {
        InitialContext context = new InitialContext();
        CamelContext camelContext = (CamelContext) context.lookup("java:jboss/camel/context/my-flow-spring-context");
        Assert.assertNotNull("Expecting camelContext to not be null", camelContext);

        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        String result = producerTemplate.requestBody("direct:start", TEST_USER_ID, String.class);
        assertEquals(VALID_JSON_RESPONSE, result);
    }
    
    @Test
    @RunAsClient
    public void testMyIntegrationService() {
    	String target = properties.getProperty(MY_FLOW_INTEGRATION_SERVICE_ENDPOINT_PROPERTY);
    	Response response = ClientBuilder.newClient().target(target)
    			.path(TEST_USER_ID)
    			.request()
    			.accept(MediaType.APPLICATION_JSON)
    			.get();
    	assertEquals(200, response.getStatus());
    	assertEquals(VALID_JSON_RESPONSE, response.readEntity(String.class));
    }
    
    @Test
    @RunAsClient
    public void testMyIntegrationServiceOn404() {
    	String target = properties.getProperty(MY_FLOW_INTEGRATION_SERVICE_ENDPOINT_PROPERTY);
    	Response response = ClientBuilder.newClient().target(target)
    			.path(INVALID_USER_ID)
    			.request()
    			.accept(MediaType.APPLICATION_JSON)
    			.get();
    	assertEquals(404, response.getStatus());
    	assertEquals(RECORD_NOT_FOUND_ERROR_RESPONSE, response.readEntity(String.class));
    }
}