FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/uber.jar uber.jar
ENTRYPOINT ["java","-jar","/uber.jar"]