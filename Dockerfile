FROM maven:3.9.9-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean install -DskipTests -X

FROM amazoncorretto:17-alpine3.17 AS runtime
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
