
# ==================== Stage 1: Build ==================

FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# Copy pom.xml first - dependecies change less frequent than src code
# This allows Docker to cache dependecy download layer
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code build
COPY src ./src
RUN mvn clean package -DskipTests -B

# ====================== Stage 2: Run ===================

FROM eclipse-temurin:17-jre-alpine

# Create a non-root user for security - never run containers as root
Run addgroup -S gymtracker && adduser -S gymtracker -G gymtracker

WORKDIR /app

# Copy only the JAR from the builder stage - nothing else comes across
COPY --from=builder /app/target/GymTracker.jar app.jar

# Change ownership of app files to non-root user
RUN chown -R gymtracker:gymtracker /app

# Switch to non-root user
USER gymtracker

EXPOSE 8087

HEALTHCHECK --interval=30s --timeout=3s --retries=3 \
    CMD wget --quiet --tries=1 --spider \
    http://localhost:8087/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]