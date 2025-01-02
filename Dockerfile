# Use a base image with Java runtime
FROM openjdk:17-jdk-slim

# Set the maintainer label (optional)
LABEL authors="John Rene"

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file to the container
COPY target/AppCode-0.0.2-SNAPSHOT.jar /app/hotel-web-app.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "hotel-web-app.jar"]
