FROM amazoncorretto:21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} superheroe.jar
ENTRYPOINT ["java","-jar","/superheroe.jar"]