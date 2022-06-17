FROM openjdk:11-jdk
ARG JAR_FILE=flatB-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} myboot.jar
ENTRYPOINT ["java","-jar","/myboot.jar"]
