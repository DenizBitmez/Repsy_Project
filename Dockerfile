FROM ubuntu:latest
LABEL authors="deniz"

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/repsy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
