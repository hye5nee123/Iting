FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
VOLUME ['/home'] 
ENV TZ=Asia/Seoul
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}
ENTRYPOINT ["java","-jar","/app.jar"]
