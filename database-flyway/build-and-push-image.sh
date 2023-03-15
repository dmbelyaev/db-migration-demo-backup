docker image build -t db-migration-job .
docker tag db-migration-job dbelyaev626/db-migration-job
docker push dbelyaev626/db-migration-job
