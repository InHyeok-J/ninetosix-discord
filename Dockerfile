FROM openjdk:17.0.2-jdk

VOLUME /tmp

WORKDIR app

ARG JAV_FILE=./build/libs/*.jar

COPY ${JAV_FILE} app.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","-Duser.timezone=Asia/Seoul","app.jar"]
