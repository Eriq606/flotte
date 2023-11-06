FROM openjdk:17-jdk-slim
ARG JAR_FILE=flotte.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
