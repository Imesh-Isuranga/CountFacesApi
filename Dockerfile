# Use a multi-stage build for Maven
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Start a new stage for the final image
FROM debian:bullseye-slim

# Install Python
RUN apt-get update && apt-get install -y python3

# Copy your Spring Boot application JAR file
COPY --from=build /target/FaceDetectApi-0.0.1-SNAPSHOT.jar demo.jar

# Expose the port
EXPOSE 8080

# Set the entry point for your Spring Boot application
ENTRYPOINT ["java","-jar","demo.jar"]
