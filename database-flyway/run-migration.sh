export DATASOURCE_URL='jdbc:postgresql://localhost:5432/local_db'
export DATASOURCE_USERNAME='user'
export DATASOURCE_PASSWORD='password'

java -jar target/db-migration-job.jar
