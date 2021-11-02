# Spring Send Mail API

This application developed for make container application. 
Basically there exist one endpoint. Post an object, send mail and insert database.
<br/>
In this application you can create containers (database, application). 

# Technologies

The project is built on Spring Boot architecture. Maven used as build automation tool.

# 1. Docker Image Create

## 1.1. Application Package

### 1.1.1. Mail Server Configuration Update

Firstly you need to update your mail server configurations.
File Path : [application.yml](./src/main/resources/application.yml)

````yaml
  spring:
      mail:
        host: smtp.gmail.com
        port: 587
        username: ${MAIL_ADDRESS}
        password: ${MAIL_PASSWORD}
        properties:
          mail:
            smtp:
              auth: true
              starttls:
                enable: true
                required: true
              ssl:
                trust: smtp.gmail.com 
````

### 1.1.2. Maven Packaging

````shell
  ./mvnw package -Dspring-boot.run.profiles=tst -DskipTests 
````

After package completed, packaged application : [spring-mail-0.0.1.jar](./target/spring-mail-0.0.1.jar)

### 1.1.2. Maven Install With Test

If you want to install or package with Test parts, you need e valid database before. 
You can run below command with valid database

````shell
  ./mvnw -DSPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mail-database install 
````

## 1.2. Image Create


### 1.2.1. DockerFile

Firstly need to create a docker file detailed below.

Path : [Dockerfile](Dockerfile)

````shell
    FROM openjdk:8-jdk-alpine
    MAINTAINER workshop.com
    COPY target/spring-mail-0.0.1.jar spring-mail.jar
    ENTRYPOINT ["java","-jar","/spring-mail.jar"]
````

### 1.2.2. Docker Build

````shell
    docker build --tag=spring-mail:latest .
````

### 1.2.3. Docker Image Created
![Docker Image Created](/assets/docker_image.jpg)

# 2. Docker Compose File

File Path : [docker-compose.yml](docker-compose.yml)
<br/>
Related SQL in this path : [create_database.sql](./sql/create_database.sql)

## 2.1. Update Valid Mail Information

````yaml
  spring-mail-application:
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql-database:3306/mail-database?autoReconnect=true&useSSL=false
      MAIL_ADDRESS: **mail address**
      MAIL_PASSWORD: **mail password**
````
`you need to give access to anonymous request at google accounts security settings`

## 2.2. Run Docker Compose

### 2.2.1. Docker Compose Command

````shell
    docker-compose -f "docker-compose.yml" up -d --build
````

### 2.2.2. Docker Compose File Content
````shell
    version: '3.8'

    networks:
      default:
    
    services:
    
      mysql-database:
        image: mysql:5.7
        container_name: mysql-database
        ports:
          - 3306:3306
        volumes:
          - ./sql/create_database.sql:/docker-entrypoint-initdb.d/create_database.sql
        environment:
          MYSQL_ROOT_PASSWORD: pass
    
    
      spring-mail-application:
        image: spring-mail:latest
        container_name: spring-mail-app
        restart: on-failure
        environment:
          SPRING_DATASOURCE_URL: jdbc:mysql://mysql-database:3306/mail-database?autoReconnect=true&useSSL=false
          MAIL_ADDRESS: workshop.emrah@gmail.com
          MAIL_PASSWORD: "*123*qwe1"
        depends_on:
          - mysql-database
        ports:
          - 8080:8080

````

`restart: on-failure` at the first try for up, couldn't connect to db. With this command restart application and successfully connect.

### 2.2.3. Docker Application Created

![Docker Application Created](/assets/docker_ps.jpg)

# 3. Send Email `/api/v1/mail/send/`

With this endpoint, requester can send email with valid spring mail configurations.

## 3.1. Request Body
```json
{
    "applicationName" : "spring-mail",
    "subject": "Hello World!",
    "to": "mail@mail.com",
    "body": "Hello World!"
}
```

### 3.2. Success Response

```json
{
    "result": true,
    "message": "Mail Successfully Sent"
}
```