FROM openjdk:11
EXPOSE 9080
ADD target/login-service.jar login-service.jar
ENTRYPOINT ["java","-jar","/login-service.jar"]