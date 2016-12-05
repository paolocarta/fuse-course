## JBoss EAP Camel Spring Maven Archetype

This is the maven archetype contains a template Apache Camel RESTful web-service for the JBoss EAP Camel subsystem. 

This project is setup to allow you to create an Apache Camel application, which can be deployed to an application server 
running the JBoss EAP Camel subsystem. An example [Spring DSL](src/main/resources/archetype-resources/src/main/webapp/META-INF/my-course-camel-context.xml)
Camel Route has been created for you.


### Prerequisites

* Minimum of Java 1.7
* Maven 3.2 or greater


### Install the archetype:

To install the archetype do:

```
# mvn clean install
```


### Generate a new project using the archetype. 

In this command, you need to specify the full information about the archetype (its groupId, its artifactId, its version) and 
the information about the new project you want to create (artifactId and groupId).  Don't forget to include the version of your 
archetype (if you don't include the version, you archetype creation may fail with a message that version:RELEASE was not found).

```
# mvn archetype:generate                                        \
  -DarchetypeGroupId=lv.jbossfuse.course                        \
  -DarchetypeArtifactId=fuse-camel-course-eap-spring-archetype  \
  -DarchetypeVersion=1.0-SNAPSHOT                               \
  -DgroupId=<my.groupid>                                        \
  -DartifactId=<my-artifactId>
```

The parameters *camel-version* and *jboss-eap-version* might be specified, otherwise the values specified by default would be taken
into account.

Every java class included in the archetype are specified as packaged, so the classes will be generated into the package specified 
as a value of the *groupId* parameter during the *mvn archetype:generate* command start.
