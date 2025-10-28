# Use a base image with Java 21
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradlew .
COPY gradlew.bat .
COPY build.gradle .
COPY settings.gradle .
COPY gradle ./gradle

# Grant execution rights to the Gradle wrapper
RUN chmod +x ./gradlew

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew build -x test

# Expose the application port
EXPOSE 8080

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "build/libs/tai_backend-0.0.1-SNAPSHOT.jar"]
