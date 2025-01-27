# First Stage: Build the application
FROM maven:3.8.1-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code
COPY src ./src

# Build the application using Maven (skip tests for faster build)
RUN mvn clean install -DskipTests

# Second Stage: Run the application
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the builder stage
COPY /target/GroceryBookingApplication-1.0.jar .

# Expose the port the application will run on
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "GroceryBookingApplication-1.0.jar"]