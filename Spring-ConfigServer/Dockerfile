FROM java:jre-11.0.6_10-alpine
WORKDIR /usr/src
ADD ./target/Spring-ConfigServer-0.0.1-SNAPSHOT.jar Spring-ConfigServer-0.0.1-SNAPSHOT.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "Spring-ConfigServer-0.0.1-SNAPSHOT.jar"]