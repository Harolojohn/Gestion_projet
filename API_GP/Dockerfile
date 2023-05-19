FROM amazoncorretto:11-alpine-jdk
MAINTAINER Harold Wojunwo
RUN addgroup -S api-gp && adduser -S api-gp -G api-gp
USER api-gp:api-gp
# Add a volume pointing to /gedoc-api
VOLUME /api-gp
# Make port 9003 available to the world outside this container
EXPOSE 9050
# The application's jar file
ARG JAR_FILE=target/Api_GP-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
COPY ${JAR_FILE} api-gp.jar
# Run the jar file 
CMD ["java", "-jar", "api-gp.jar" ,"--spring.config.location=file:/api-gp/config/application.properties" ]


