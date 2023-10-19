# Use a base image with Python (e.g., Debian with Python 3)
FROM python:3.8-slim as python-base

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the Maven project files and build
COPY pom.xml .
COPY src src/
RUN mvn clean package -DskipTests

# Use a minimal JRE base image
FROM openjdk:17.0.1-jdk-slim

# Copy the built JAR from the previous stage
COPY --from=python-base /app/target/FaceDetectApi-0.0.1-SNAPSHOT.jar demo.jar

# Expose the port
EXPOSE 8080

# Set the entry point for your Spring Boot application
ENTRYPOINT ["java","-jar","demo.jar"]
