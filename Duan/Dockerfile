# Stage 1: Build
FROM maven:3.9.8-amazoncorretto-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml trước để cache dependencies
COPY pom.xml .

# Download dependencies

# Copy source code
COPY src ./src

# Build ứng dụng
RUN mvn package -DskipTests

# Stage 2: Run
FROM amazoncorretto:21.0.4

WORKDIR /app

# Copy file jar từ stage build
COPY --from=build /app/target/*.jar app.jar

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
