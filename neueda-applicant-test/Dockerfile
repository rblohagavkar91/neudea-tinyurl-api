FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/neueda-applicant-test-1.0.0-SNAPSHOT.jar tinyurl.jar
ENTRYPOINT ["java","-jar","/tinyurl.jar"]