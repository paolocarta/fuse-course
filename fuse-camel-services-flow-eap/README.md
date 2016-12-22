## JBoss EAP Camel RESTful Web-service

This is a Apache Camel RESTful web-service exposes a simple flow for the JBoss EAP Camel subsystem. 

This project is setup to allow you to create a simple Apache Camel flow (Spring DSL is in use), which can be deployed 
to an application server running the JBoss EAP Camel subsystem. An example [Spring XML](src/main/webapp/META-INF/jboss-camel-context.xml)
Camel Route has been created for you.

The flow is the following:

`[UserId request]` --> `[PhoneBook RESTFul service]` --> `[MonthlyReceipt SOAP service]` --> `[Convert SOAP to JSON]` 
--> `[User Data Response]`

The flow also demonstrates the exception handling mechanism.

### Prerequisites

* Minimum of Java 1.7
* Maven 3.2 or greater
* JBoss EAP 6.4


### Getting started at the Command Line

1. Istall JBoss Fuse on your application server

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

Regardless, the endpoint locations contained in the `test\resources\test-endpoints.properties` file may be changed to address 
your installation of the application server.


### Deploying the application

To deploy the application to a running application server do:

```
# mvn clean package wildfly:deploy
```

The server console should display lines like the following:

```
(MSC service thread 1-16) Apache Camel (CamelContext: my-flow-spring-context) is starting
(MSC service thread 1-16) Camel context starting: my-flow-spring-context
(MSC service thread 1-16) Bound camel naming object: java:jboss/camel/context/my-flow-spring-context
(MSC service thread 1-16) Route: _route1 started and consuming from: Endpoint[direct://start]
(MSC service thread 1-16) Total 1 routes, of which 1 is started
```


### Access the application

The application will be available at the following resource: 

* <http://localhost:8080/fuse-camel-services-flow-eap/services/monthlyreceipt/ki10105>

The last segment of the URL is a value of the `UserId` parameter. The deployed implementation of the `PhoneBook` service
contains an in-memory repository, which caches two available user ids from different regions (these users are needed for
the Camel Dynamic Routing capability demonstration).


### Undeploying the application

```
# mvn wildfly:undeploy
```


### Further reading

Apache Camel documentation

http://camel.apache.org/

JAX-RS Tutorial

http://www.mkyong.com/tutorials/jax-rs-tutorials/

Pavel Samolysov's Blog (Russian)

http://samolisov.blogspot.com/
