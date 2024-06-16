FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/CA5_MizDooni-0.0.1-SNAPSHOT.jar mizdooni.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "mizdooni.jar"]