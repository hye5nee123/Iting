FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
VOLUME ['/home'] 
# spring profile
ENV SPRING_PROFILE "linux"

ENV TZ=Asia/Seoul

ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILE}","-jar","/app.jar"]