# Build stage
FROM maven:3.9.5-eclipse-temurin-21 AS build
WORKDIR /build
# Copy POM first to cache dependencies
COPY pom.xml .
# Download dependencies
RUN mvn dependency:go-offline
# Copy source code
COPY src ./src
# Build application
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app
# Copy only the built JAR from build stage
COPY --from=build /build/target/moneymanager-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
# Expose port
EXPOSE 9090
# Run with simpler entry point
ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]