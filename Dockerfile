FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ADD target/*.jar qs-spring-boot-docker.jar
#
#ENTRYPOINT ["java","-jar","qs-spring-boot-docker.jar"]

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["java","-jar","/home/kevin-m/IntellijProjects/qs/target/qs-spring-boot-docker.jar"]