FROM openjdk:latest
COPY ./target/classes/com /tmp/com
WORKDIR /tmp
