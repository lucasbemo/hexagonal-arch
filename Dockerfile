FROM maven:3.8.4-eclipse-temurin-17 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
RUN ./gradlew clean build

#FROM eclipse-temurin:17.0.2_8-jre-alpine@7650e1c259b69f38803515e8e0257cab4241e17084295c3b84cc32075b1bada0
FROM arm64v8/eclipse-temurin:17-jre
#RUN apk add dumb-init
RUN mkdir /app
RUN addgroup javaapp && adduser javauser && javauser javaapp
COPY --from=build /project/hexagonal-arch-application/build/libs/hexagonal-arch-application-0.0.1.jar /app/java-application.jar
WORKDIR /app
RUN chown -R javauser:javauser /app
USER javauser
CMD "java" "-Dspring.profiles.active=local" "-jar" "java-application.jar"