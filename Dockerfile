FROM openjdk:11
COPY target/Uber.jar Uber.jar
ENTRYPOINT ["java","-jar","/Uber.jar"]