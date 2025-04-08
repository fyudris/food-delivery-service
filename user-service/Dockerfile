# Use an official JDK image to build the app
FROM gradle:8.4.0-jdk17 AS builder

WORKDIR /app
COPY . .

# Build the app using Gradle (without tests to speed it up)
RUN gradle bootJar --no-daemon

# Use a smaller image to run the app
FROM openjdk:17-jdk-slim

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port 8080 (default Spring Boot port)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]