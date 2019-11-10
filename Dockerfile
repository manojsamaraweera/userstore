FROM openjdk:8-jdk-alpine
LABEL maintainer="manojsamaraweera@gmail.com"
VOLUME /tmp
EXPOSE 8082
ARG JAR_FILE=target/userstore-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} userstore.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/userstore.jar"]

