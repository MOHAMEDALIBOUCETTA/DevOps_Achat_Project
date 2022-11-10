FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD target/Uber.jar Uber.jar
ENTRYPOINT ["java","-jar","/Uber.jar"]