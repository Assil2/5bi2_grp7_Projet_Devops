
# Use an OpenJDK image as the base
FROM openjdk:17-jdk-alpine

# Construct the Nexus download URL based on these arguments
RUN apk add --no-cache curl && \
    curl -o app.jar "http://192.168.33.10:8081/repository/maven-nexus-repo/tn/esprit/tp-foyer/5.0.0/tp-foyer-5.0.0.jar"

# Expose the application port
EXPOSE 8089

ENTRYPOINT ["java", "-jar", "app.jar"]