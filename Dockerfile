FROM openjdk:11
COPY target/study-api.jar /usr/app/
WORKDIR /usr/app/
ENTRYPOINT ["java", "-jar", "study-api.jar"] 

