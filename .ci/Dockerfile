FROM openjdk:21-jdk-slim AS build

COPY . /usr/src/app

RUN apt update && apt install -y maven
RUN mvn -f /usr/src/app/pom.xml install -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /usr/src/app/target/telegram-auth-0.0.1-SNAPSHOT.jar /usr/app/telegram-auth-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/app/telegram-auth-0.0.1-SNAPSHOT.jar"]
