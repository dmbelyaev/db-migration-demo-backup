docker run -p 8080:8080 -e SPRING_DATASOURCE_URL='jdbc:postgresql://host.docker.internal:5432/local_db' db-migration-app
