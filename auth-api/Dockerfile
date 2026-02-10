# ---------- Stage 1: build ----------
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /auth-api

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# ---------- Stage 2: run ----------
FROM eclipse-temurin:21-jre

WORKDIR /auth-api

COPY --from=build /auth-api/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
