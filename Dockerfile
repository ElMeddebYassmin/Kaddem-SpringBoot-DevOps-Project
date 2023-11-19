## Use the Maven image to build the application
#FROM maven:3.8.3-openjdk-11 AS builder
#WORKDIR /app
#COPY pom.xml .
#RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline
#COPY src/ src/
#RUN --mount=type=cache,target=/root/.m2 mvn package
#
## Create the runtime container
#FROM openjdk:11-jre-slim
#EXPOSE 8085
#RUN apt-get update && apt-get install -y curl jq
#
## Define Nexus URL and repository information
#ARG NEXUS_URL="http://192.168.33.10:8081/repository/KaddemApp/"
#ARG ARTIFACT_NAME="khaddem"
#
## Download the latest snapshot JAR from Nexus
#RUN LATEST_VERSION=$(curl -s "${NEXUS_URL}service/rest/v1/search/assets/download?repository=KaddemApp&name=${ARTIFACT_NAME}&maven.extension=jar" | jq -r '.items[0].version' | sed 's/\-.*//') && \
#    curl -o /khaddem-0.0.1.jar "${NEXUS_URL}tn/esprit/spring/khaddem/${LATEST_VERSION}/${ARTIFACT_NAME}-${LATEST_VERSION}.jar"
#
## Set Java options and run the application
#ENV JAVA_OPTS="-Dlogging.level.org.springframework.security=DEBUG -Djdk.tls.client.protocols=TLSv1.2"
#ENTRYPOINT ["java", "-jar", "/khaddem-0.0.1.jar"]









FROM openjdk:11
EXPOSE 8085
ADD target/khaddem-0.0.1-SNAPSHOT.jar khaddem-0.0.1.jar
CMD java -jar achat-0.0.1.jar
ENTRYPOINT ["java","-jar","/khaddem-0.0.1.jar"]
