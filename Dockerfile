FROM openjdk:12-alpine
MAINTAINER Antonia Luciana Pires
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
COPY ./target/vuttr-0.0.1-SNAPSHOT.jar $PROJECT_HOME
WORKDIR  $PROJECT_HOME
ENTRYPOINT ["java", "-jar","vuttr-0.0.1-SNAPSHOT.jar"]