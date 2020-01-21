FROM openjdk:latest
COPY ./target/classes /tmp/classes
WORKDIR /tmp
ENTRYPOINT ["java", "org.example.TestApp"]