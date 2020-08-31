FROM openjdk:11
ADD target/gateway-service.jar gateway-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway-service.jar"]