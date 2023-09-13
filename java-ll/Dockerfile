FROM eclipse-temurin:17-jdk-focal
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/Entregable-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]