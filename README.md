# ECHO Mother App 

Prerequisites to run the project at local environment:

  - Spring Tool Suite IDE (STS)
  - Lombok
 
### Spring Tool Suite IDE
We use Spring Tool Suite IDE and you can download it from here: 

[![N|Solid](https://i2.wp.com/blog.fabianpiau.com/wp-content/uploads/2016/03/spring.png?resize=128%2C128&ssl=1)](https://spring.io/tools) 

### Lombok
We have Lombok dependency on our project and to import it to STS IDE, you have to download Lombok jar file from: 
https://projectlombok.org/download

Run the Lombok jar file: 
```sh
$ java -jar lombok-file-name.jar
```
Specify location of STS IDE and install Lombok. 

### Spring Profiles

We are using Spring Profiles to be able to run the application in different enviroments and behaviours. Active profile can be set in application.yml file by following:

```sh
spring:
  profiles:
    active: development
```

Options for the active profiles are:

| Profile | Description |
| ------ | ------ |
| development | Enables to run the project locally with a in-memory (H2) database. Environment variables are set by EnvironmentVariablesInitializer.class automatically for connection strings and tenant ids. Database is also populated with a initial mock data  |
| production | Application reads the connection strings and tenant ids from environment variables and tries to connect PostgreSQL servers for connection strings. This profile is designed for Heroku but you can run application in this profile if you set environment variables in your locale enviroment.  |
| test | This profile is for test purposes |

### Running The Project

First, clone the project and install maven dependencies. 

```sh
$ https://github.com/anilkara/echo-mother-app.git
$ cd echo-mother-app/
$ mvn clean install
```
We recommend you to run application in "Development" profile, which is the default option if you don't have a reason to run in another profile.

You can run application from STS IDE by "Run as Spring Boot App" option or with following command:

```sh
$ mvn spring-boot:run
```
Application listens port 8080.

If you run application in "Development" profile, there are 3 different databases for 3 different tenants.
The tenant ids are:

  - tenant1
  - tenant2
  - tenant3

### Licence
