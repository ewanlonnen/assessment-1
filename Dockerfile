FROM openjdk:latest
COPY ./target/Assessment-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "Assessment-1.0-SNAPSHOT-jar-with-dependencies.jar"]