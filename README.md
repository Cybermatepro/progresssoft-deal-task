# progressSoft - ClusteredData - Warehouse

### Introduction

Develop data warehouse for Bloomberg to analyze FX deals

---

### Task description

One of customer stories is to accept deals details from and persist them into DB.


### Technologies

- Java
- Maven
- MySQL
- Spring Boot
- Spring Data JPA

### Requirements

You need the following to build and run the application:

- [JDK 17](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3.8.6](https://maven.apache.org) (This is the build tool used.)


## How to run Application(s)
### step 1 - clone project with Terminal from [here](https://github.com/johnDevALX/progressSoft-fxDeal_task)

```
git clone git@github.com:johnDevALX/progressSoft-fxDeal_task.git
```

### step 2 - move into the project directory
```
cd clusteredData-warehouse/
```

### step 3 - Open the clusteredData-wareHouse Folder in an IDE, As a maven Project.
 
```
mvn spring-boot:run
```


### step 6 - Generate the .jar files with Terminal

```
mvn clean install 
```
OR
```
./mvnw clean install
```


## PostMan Collection for Integration Tests.
- clusteredDataWarehouse [here](https://api.postman.com/collections/20569888-227bc66d-5542-44ac-bee5-f7ccbbb3550c?access_key=PMAT-01H6BHMAV9S6D8K2V15MNSANMK)


## Running The Tests with Maven

To run the tests with maven, you would need to run the maven command for testing, after the code has been compiled.
```
mvn <option> test
```


# Contributing
We welcome contributions to the FX Deals Data Warehouse project. If you have any ideas, bug fixes, or enhancements, please submit a pull request.
