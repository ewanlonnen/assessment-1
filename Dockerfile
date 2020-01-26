FROM openjdk:latest
COPY ./target/classes/ /tmp/classes
WORKDIR /tmp/classes
ENTRYPOINT ["java", "TestApp"]