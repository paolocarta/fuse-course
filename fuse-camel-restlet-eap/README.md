## JBoss EAP Camel RESTful Web-service

This is a template Apache Camel RESTful web-service for the JBoss EAP Camel subsystem. 

This project is setup to allow you to create an Apache Camel application (Spring or Java DSLs are in use), which can be deployed 
to an application server running the JBoss EAP Camel subsystem. An example Spring XML Camel Route as well as 
[Java DSL](src/main/java/lv/jbossfuse/course/restlet/MyCamelContextBuilder.java) one have been created 
for you.


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


### Deploying the application

To deploy the application to a running application server do:

```
# mvn clean package wildfly:deploy
```

The server console should display lines like the following:

```
(MSC service thread 1-16) Apache Camel (CamelContext: my-spring-context) is starting
(MSC service thread 1-16) Camel context starting: my-spring-context
(MSC service thread 1-16) Bound camel naming object: java:jboss/camel/context/my-spring-context
(MSC service thread 1-16) Route: _route1 started and consuming from: Endpoint[direct://start]
(MSC service thread 1-16) Route: invokeSayHello started and consuming from: Endpoint[direct://invokeSayHello]
(MSC service thread 1-1) Route: invokeGreet started and consuming from: Endpoint[direct://invokeGreet]
(MSC service thread 1-16) Total 3 routes, of which 3 is started
```


### Access the application

The application will be available at http://localhost:8080/fuse-camel-restlet-eap/services/hello/sayhello


### Undeploying the application

```
# mvn wildfly:undeploy
```


### Further reading

Apache Camel documentation

http://camel.apache.org/

Pavel Samolysov's Website

http://psamolysov.blogspot.com/
