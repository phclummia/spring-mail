FROM openjdk:8-jdk-alpine
COPY target/spring-mail-0.0.1.jar spring-mail.jar
ENTRYPOINT ["java","-jar","/spring-mail.jar"]