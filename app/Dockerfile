FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/db-migration-demo-app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
