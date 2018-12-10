# DS-RESTful-webservice-webclient
---
## Functionality.
This repo contains the source code to a car hire booking system.
This system is written in java using Jersey, Java RMI and JAXB frameworks.
It contains three core components
1. Web Client.
2. Restful Web Server.
3. RMI database server
The webclient was created using jsp to display and send data to/from the resful webserver.
The restful webserver connects to the rmi database server and handles the marsheling and un marshaling to xml between the webclient and server.
the RMI database server has database CRUD functionality implemented in remotely called methods accesable from the restful webserver.

---
## Setup.
1. Create Maven project in Eclipse Enterprise Edition. which can be downloaded at https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers
2. Download and install TomCat and add the server to your eclipse enviorment. the newest version of tomcat can be downloaded at
https://tomcat.apache.org/download-70.cgi
3. In your maven eclipse project you should find pom.xml file you must add the jersey dependancey to this file as follows.
```
<dependency>
<groupId>com.sun.jersey</groupId>
<artifactId>jersey-server</artifactId>
<version>1.19</version>
</dependency>
<dependency>
<groupId>com.sun.jersey</groupId>
<artifactId>jersey-servlet</artifactId>
<version>1.19</version>
</dependency>
<dependency>
<groupId>org.apache.tomcat</groupId>
<artifactId>servlet-api</artifactId>
<version>6.0.44</version>
</dependency>
```
4. import the java files found in this repository at/sw/src/main/java/ie/gmit/sw/ into a package that matches the package name
package ie.gmit.sw
5. import the jsp files found in this repositoy at /sw/src/main/webapp/ into the webapp folder of your reposity.
6. import jar files found in the lib folder of this directory into your enviorment class path.
---
## Running.
To run this application simply right click on the servicesetup java file and run as a java application this will start the rmi server.
the project can then be run on your installed tomcat server.

