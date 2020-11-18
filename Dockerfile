FROM maven:3.6.3-jdk-8-slim AS build
COPY src /project/src
COPY pom.xml /project
RUN mvn -f /project/pom.xml clean package

FROM openjdk:8
COPY --from=build /project/target/PFSwChOProject-1.0.0-SNAPSHOT.jar /project/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/project/app.jar"]