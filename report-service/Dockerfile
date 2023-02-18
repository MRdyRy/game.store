FROM openjdk:11
EXPOSE 9050
ADD target/report-service.jar report-service.jar
ENTRYPOINT ["java","-jar","/report-service.jar"]