docker-compose down

docker rmi farins/superherodemoapp:latest

mvn clean install -f ./pom.xml

docker build -t farins/superherodemoapp:latest -f ./Dockerfile ./

docker-compose up -d

echo "Docker compose up. Running Migrations Soon"
sleep 10

mvn flyway:migrate -f ./pom.xml

echo "Migrations completed. Process finished."