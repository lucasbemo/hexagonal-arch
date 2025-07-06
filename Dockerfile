FROM maven:3.9.10-eclipse-temurin-21-alpine AS build

RUN mkdir /app

COPY . /app

WORKDIR /app

RUN chmod +x mvnw

CMD ["mvn", "clean", "install"]

FROM eclipse-temurin:21-alpine

RUN mkdir /app

RUN addgroup --system javauser && adduser --system --ingroup javauser javauser

COPY --from=build /app/hexagonal-arch-application/target/hexagonal-arch-application-0.0.1.jar /app/java-application.jar

WORKDIR /app

RUN chown -R javauser:javauser /app

USER javauser

CMD "java" "-Dspring.profiles.active=local" "-jar" "java-application.jar"
