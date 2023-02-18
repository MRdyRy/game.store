FROM openjdk:11
EXPOSE 9090
ADD target/auth-service.jar auth-service.jar
ENTRYPOINT ["java","-jar","/auth-service.jar"]