FROM java:jre-11.0.6_10-alpine
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/MovieCruiser
ADD ./target/UserProfileService-0.0.1-SNAPSHOT.jar UserProfileService-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "UserProfileService-0.0.1-SNAPSHOT.jar"]