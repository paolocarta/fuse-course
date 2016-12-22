## JBoss EAP Camel SOAP Web-Service

This is a template Apache Camel SOAP Web-Service for the JBoss EAP Camel subsystem. 

This project is setup to allow you to create a Apache Camel application (Java DSL is in use), which can be deployed to an 
application server running the JBoss EAP Camel subsystem. An example 
[Java DSL](src/main/java/lv/jbossfuse/course/ws/MyCourseCamelContextBuilder.java) Camel Route has been created for you, together
with an Arquillian [integration test](src/test/java/lv/jbossfuse/course/ws/test/MyCourseEndpointTest.java). The application also 
demonstrates how to deal with the filter and beans API.


### Prerequisites

* Minimum of Java 1.7
* Maven 3.2 or greater
* JBoss EAP 6.4


### Getting started at the Command Line

1. Install JBoss Fuse on your application server

2. Configure a $JBOSS_HOME environment variable to point at your application server installation directory

3. Start the application server from the command line

For Linux:
```
$JBOSS_HOME/bin/standalone.sh -c standalone.xml
```

For Windows:
```
%JBOSS_HOME%\bin\standalone.bat -c standalone.xml
```


### Building the application

To build the application do:

```
# mvn clean install
```


### Run Arquillian Tests
    
By default, tests are configured to be skipped as Arquillian requires the use of a container.

If you already have a running application server, you can run integration tests with:

```
# mvn clean test -Parq-remote
```

Otherwise you can get Arquillian to start and stop the server for you (Note: you must have $JBOSS_HOME configured beforehand):

```
# mvn clean test -Parq-managed
```


### Deploying the application

To deploy the application to a running application server do:

```
# mvn clean package wildfly:deploy
```

The server console should display lines like the following:

```
(MSC service thread 1-5) Camel CDI is starting Camel context [my-course-camel-context]
(MSC service thread 1-5) Apache Camel (CamelContext: my-course-camel-context) is starting
(MSC service thread 1-5) Camel context starting: my-course-camel-context
(MSC service thread 1-4) Bound camel naming object: java:jboss/camel/context/my-course-camel-context
(MSC service thread 1-5) Route: route3 started and consuming from: Endpoint[direct://ws]
(MSC service thread 1-5) Total 1 routes, of which 1 is started
```


### Access the application

The definition of the web-service can be available at <http://localhost:8080/fuse-camel-ws-eap/MyCourseEndpointService?wsdl>
This definition may be loaded into a **Soap UI** project. An example project is put into the 
[test/resources](src/test/resources/MyCourseEndpointService-soapui-project.xml) directory.


### Undeploying the application

```
# mvn wildfly:undeploy
```


### Further reading

Apache Camel documentation

http://camel.apache.org/

Pavel Samolysov's Blog (Russian)

http://samolisov.blogspot.com/
