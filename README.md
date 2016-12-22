# JBoss Fuse Courses

A collection of projects developed for the JBoss Fuse courses conducted in Riga, Latvia.

 * [fuse-camel-course-eap-spring-archetype](fuse-camel-course-eap-spring-archetype) - a maven archetype that contains a template 
   Apache Camel RESTful web-service for the JBoss EAP Camel subsystem. 
 * [fuse-camel-restlet-eap](fuse-camel-restlet-eap) - a template Apache Camel RESTful web-service for the JBoss EAP Camel subsystem.
 * [fuse-camel-ws-eap](fuse-camel-ws-eap) - a template Apache Camel SOAP Web-Service for the JBoss EAP Camel subsystem.
 * [fuse-camel-services-flow-eap](fuse-camel-services-flow-eap) - an Apache Camel RESTful web-service that exposes a simple flow for 
   the JBoss EAP Camel subsystem. The project shows how to implement a service orchestration (for REST as well as SOAP services)
   upon Apache Camel.


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


### Further reading

Apache Camel documentation

http://camel.apache.org/

Pavel Samolysov's Blog (Russian)

http://samolisov.blogspot.com/
