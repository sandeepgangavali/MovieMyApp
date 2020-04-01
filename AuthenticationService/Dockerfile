FROM java:jre-11.0.6_10-alpine
WORKDIR /usr/src
ADD ./target/AuthenticationService-0.0.1-SNAPSHOT.jar AuthenticationService-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "AuthenticationService-0.0.1-SNAPSHOT.jar"]




