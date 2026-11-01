
# Stage 1: Cache dependencies and build the app
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /build

# Copy build files first to leverage Docker layer caching
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

# Copy source code and build the package
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# Extract the JAR layers for a modular runtime image
RUN mkdir -p target/extracted && \
    java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted

# Stage 2: Production-hardened minimal JRE runtime
FROM eclipse-temurin:17-jre-alpine AS runtime
WORKDIR /app

# Create a non-root system user and group for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy the extracted layers from the builder stage
COPY --from=builder /build/target/extracted/dependencies/ ./
COPY --from=builder /build/target/extracted/spring-boot-loader/ ./
COPY --from=builder /build/target/extracted/snapshot-dependencies/ ./
COPY --from=builder /build/target/extracted/application/ ./

# Switch to the non-root user
USER spring:spring

# Expose app port (adjust if your service runs on a different port)
EXPOSE 8080

# Production JVM optimizations and container-aware flags
ENTRYPOINT ["java", \
            "-server", \
            "-XX:+UseG1GC", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "org.springframework.boot.loader.launch.JarLauncher"]
