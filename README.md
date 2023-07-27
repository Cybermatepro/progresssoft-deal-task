# progressSoft - ClusteredData - Warehouse

### Introduction

Develop data warehouse for Bloomberg to analyze FX deals

---

### Task description

Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB.


### Request logic as following:

Request Fields(Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
System should not import same request twice.
No rollback allowed, what every rows imported should be saved in DB.

### Deliverables should be ready to work including:

- Use Actual Db, you can select between (Postgres, MySql or MongoDB)
- Workable deployment including sample file (Docker Compose).
- Maven or Gradle project is required for full source code.
- Proper error/exception handling.
- Proper Logging.
- Proper unit testing with respected Coverage.
- Proper documentation using md.
- Delivered over Githhub.com.
- Makefile to streamline running application (plus).


### Technologies

- Java version 11
- Maven 3.8
- Postgres
- Spring Boot version 2.7.4
- Spring Data JPA

### Requirements

You need the following to build and run the application:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.6](https://maven.apache.org) (This is the build tool used.)


## How to run Application(s)
### step 1 - clone project with Terminal from [here](https://github.com/johnDevALX/progressSoft-fxDeal_task)

```
git clone git@github.com:Cybermatepro/progresssoft-deal-task.git
```

### step 2 - move into the project directory
```
cd progresssofttask/
```

### step 3 - Open the progresssofttask Folder in an IDE, As a maven Project.
 

```
mvn clean install 
```
OR
```
./mvn clean install -DskipTests
```


## PostMan Collection for Integration Tests.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/20569888-40a8d1f0-824e-487c-8b4c-a39b52d26a2a?action=collection%2Ffork&collection-url=entityId%3D20569888-40a8d1f0-824e-487c-8b4c-a39b52d26a2a%26entityType%3Dcollection%26workspaceId%3D988ca3d3-0cee-4e0a-8ed0-93af1abf998d#?env%5BProgresssoft%20Env%5D=W3sia2V5IjoidXJsIiwidmFsdWUiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJlbmFibGVkIjp0cnVlLCJ0eXBlIjoiZGVmYXVsdCJ9XQ==)
## Running The Tests with Maven

To run the tests with maven, you would need to run the maven command for testing, after the code has been compiled.
```
mvn <option> test
```


# USING DOCKER

### Quick reference
Maintained by:
Enwere Vincent



### Supported tags and respective Dockerfile links
- 1.0.0
- 1.0.1
- 1.0.3

## How to use this image
start a postgres instance
$ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres
The default postgres user and database are created in the entrypoint with initdb.

The postgres database is a default database meant for use by users, utilities and third party applications.

postgresql.org/docs

... or via psql
```
$ docker run -p 8080:80 -e DB_USER = 'postgres' -e DB_NAME= 'progresssoft_db' -e DB_PASS = 'postgres' -e DB_DRIVER = 'org.postgresql.Driver' -e DB_PORT = '5432' vinlyreeks/progresssoft:1.0.3
```


----------


### ... via docker-compose or docker stack deploy
Example docker-compose.yml for progresssoftask:



```

version: '3.8'

services:
  postgres-compose:
    image: postgres
    networks:
      - iw-network
    ports:
      - "9432:5432"
    environment:
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_USER=postgres
      - POSTGRES_DB=progresssoft_db
  progresssoft:
    image: vinlyreeks/progresssoft:1.0.1
    depends_on:
      - postgres-compose
    networks:
      - iw-network
    deploy:
      restart_policy:
        condition: on-failure
        delay: 50s
        max_attempts: 3
        window: 120s
    ports:
      - "8090:8080"
    environment:
      DB_HOST:  postgres-compose
      DB_DRIVER: org.postgresql.Driver
      DB_USER: postgres
      DB_PASS: postgres
      DB_NAME: progresssoft_db
      DB_PORT: 5432

networks:
  iw-network:

   
```

