Find the steps to run REST web service application and angular application.

1. Run REST Web Service using Spring Boot

1. Using Eclipse: go to the attached folder import spring-boot-demo as maven project project and run.
 Mvn clean eclipse:eclipse
and then refresh the project in eclipse. Run Main class MyApplication by clicking Run as -> Java Application. Embedded tomcat server will be started. 

2. Using Maven Command: Download the web service project source code. Go to the root folder of the project using command prompt and run the command.
mvn spring-boot:run
Embedded tomcat server will be started. 

3. Using Executable JAR: Using command prompt, go to the root folder of the project and run the command.
Mvn clean package 
We will get executable JAR spring-boot-demo-0.0.1-SNAPSHOT.jar in target folder. Run this JAR as
java -jar target/spring-boot-demo-0.0.1-SNAPSHOT.jar
Embedded tomcat server will be started.

2. Run Angular 2 Application using Angular CLI
To run the angular application, find the following steps. 
1. Install Angular CLI QUICKSTART using the link. 
2. Download Angular project source code using download link given on this page in download section. 
3. In your angular CLI application, replace src folder by the downloaded one. 
4. Run ng serve command. 
5. Our Angular application is ready on the following URL.
http://localhost:4200


