### Applicant test : Neueda ###

Description : Tiny URL creation and getting original url from tiny.

Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook
feeds. These are examples of shortened URLs, which are a short alias or pointer to a
longer page link. For example, I can send you the shortened
URL http://bit.ly/SaaYw5 that will forward you to a very long Google URL with search
results on how to iron a shirt. 

Requirements
•	Design and implement an API for short URL creation
•	There should be some form of persistent storage, but don’t waste too much time on database configuration – simple file holding records or transitory runtime object is enough,
•	Implement forwarding of short URLs to the original ones,
•	Assume application will be distributed as Docker image. Provide Dockerfile, but don’t waste too much time for building and testing docker image, focus on functionality. 
•	Assume importance levels:
1 – Code working as described in requirements,
2 – Application is building with simple javac, mvn install or gradle build command (or any basic build command working on behalf of programming language you choose),
3 -  Unit tests are included. Coverage level depends on time you have left to complete the assignment, but we would like to see business logic (service layer) coverage at 60%,
4 – Other things you would like to implement for this project (ex. Database, application test coverage over 90%, API for gathering different statistics,  UI or whatever else you think would make your application extraordinary),

Solution Provided as below :-

### Technology used ###
1. Java 1.8
2. Spring Boot 2.4.3
3. Junit	
4. Swagger UI
5. Mysql 8.0
6. Docker 20.10.5

###Service Detail ###

## How to Run the application ##

##Run application without docker

Clone the project in Eclipse and do the below steps -
1. Right click on the project > Maven > Update Project
2. Right click on the project > Run As > Maven Clean
3. Right click on the project > Run As > Maven install
4. Right click on PathProcessApiApplication.java(From the base package com.neueda.urlprocess) and Run As > Java Application

##Run application without docker by two ways as given below.
prerequisite : Docker already installed and running condition.

#First Way : Application can be run through Docker commands by networking(As Spring boot connecting to mysql, there are two networks in comminication). Below steps need to be execute and application can be available to access. Below commands need to be executed in command prompt in the projects directory where Dockerfile.txt presents. 
 
 1. docker build -t neuedaapplicanttest .
 2. docker pull mysql:8.0
 3. docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=admin -e  MYSQL_DATABASE=urlprocessschema -e MYSQL_USER=root -e 	MYSQL_PASSWORD=admin -d mysql:8.0
 4. docker container logs mysql-standalone
 5. docker run -d -p 8080:8080 --name neuedaapplicanttest --link mysql-standalone:mysql neuedaapplicanttest 

#Second Way : Application can be executed by executing docker-compose.yml file added in project structure. That file contains all commands/steps require to run the application in docker. To execute docker-compose.yml file, below commands need to be executed in command prompt in the projects directory where docker-compose.yml file presents.

1. docker build -t neuedaapplicanttest .
2. docker-compose up


#Application can be easily tested by below URL. It will provide Swagger UI to test the service.
http://localhost:8080/swagger-ui.html

Under url-process-controller, you will able to see two API(createshorturl and getOrigional url) to test. 

1. createshorturl

Input :

If want to test through postman/Soap, URL :- http://localhost:8080/api/longUrl

{"longUrl": "https://www.toolsqa.com/postman/post-request-in-postman/"}

output :

{
  "tinyUrlResponse": "http://bit.ly/p"
}

----------------------
2. getOriginalUrl
Input : 

If want to test through postman/Soap, 
URL :- 
http://localhost:8080/api/shortUrl/p

Output :
{"tinyUrlResponse": "https://www.toolsqa.com/postman/post-request-in-postman/"}

--------------------- 
## What could I do more(Improvement points)
Exception handling could be improved at granular level
