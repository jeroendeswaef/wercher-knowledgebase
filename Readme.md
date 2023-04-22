## Running

To run it in a local docker container:

    docker run -it --rm -p 8080:8080 -v /var/tmp/.m2:/root/.m2 -w /opt/host -v $PWD/:/opt/host eclipse-temurin:17.0.6_10-jdk-focal ./mvnw spring-boot:run

