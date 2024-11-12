FROM openjdk:17-jdk-slim-buster
ADD target/tp-foyer-5.0.0.jar tp-foyer-5.0.0.jar
EXPOSE 8089
ENTRYPOINT ["java","-jar","/tp-foyer-5.0.0.jar"]