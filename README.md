# YouSpace

A simple blog written in Java using Spring-Boot, Thymeleaf and MongoDB as database.

## Setup

1. Clone this repository:
```
git clone https://github.com/msemjan/YouSpace.git
```

2. Build the project. At the moment, it will run only in your Intellij IDE, but if you want to deploy this app it is a good idea to build a `.war` file, which can be deployed in something like Tomcat server

## Settings

Application settings are in `./src/main/resources/application.properties`.
```properties
spring.data.mongodb.database=<name of your MongoDB database>
spring.data.mongodb.host=<host>
spring.data.mongodb.port=<port at which runs MongoDB>
server.port=<port at which you want your application to run>

spring.security.user.name = <username who can access the application>
spring.security.user.password = <password>
```