FROM openjdk:8-alpine
COPY target/university-0.0.1-SNAPSHOT.jar university-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/university-0.0.1-SNAPSHOT.jar"]