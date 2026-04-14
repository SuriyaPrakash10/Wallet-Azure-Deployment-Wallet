# Step 1: Build the jar
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app
# Copy entire project from parent context
COPY . .

# Build just the Wallet module (note: case-sensitive!)
RUN mvn clean package -DskipTests

# Step 2: Run the app
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
# Fix: builder (not builderm) and correct path
COPY --from=builder /app/Wallet/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]