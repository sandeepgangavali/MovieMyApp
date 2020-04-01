FROM java:jre-11.0.6_10-alpine
WORKDIR /usr/src
ADD ./target/Netflix-ZuulAPIGatewayService-0.0.1-SNAPSHOT.jar Netflix-ZuulAPIGatewayService-0.0.1-SNAPSHOT.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "Netflix-ZuulAPIGatewayService-0.0.1-SNAPSHOT.jar"]