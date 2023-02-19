FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/rudy/codility/game.store/customer-management-service/src
COPY pom.xml /home/rudy/codility/game.store/customer-management-service
RUN mvn -f /home/rudy/codility/game.store/customer-management-service/pom.xml clean package

FROM openjdk:11
EXPOSE 9070
ADD target/customer-management-service.jar customer-management-service.jar
ENTRYPOINT ["java","-jar","/customer-management-service.jar"]