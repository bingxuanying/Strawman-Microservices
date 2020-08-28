FROM openjdk:11
ADD target/data-service.jar data-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "data-service.jar"]