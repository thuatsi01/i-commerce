FROM maven:3.8-openjdk-11-slim
COPY /rest-apis/target/rest-apis.jar /apis/
ENTRYPOINT java \
 -jar /apis/rest-apis.jar