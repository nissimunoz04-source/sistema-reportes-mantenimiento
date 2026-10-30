
# ETAPA 1: Compilar el proyecto con Maven y Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn -B -DskipTests package

# ETAPA 2: Ejecutar la aplicacion con Java 21
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=65.0", "-XX:ActiveProcessorCount=2", "-jar", "app.jar"]