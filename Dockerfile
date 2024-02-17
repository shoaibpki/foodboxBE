FROM openjdk:11
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} abctelecom.jar
ADD target/*.jar foodbox.jar
ENTRYPOINT ["java", "-jar", "/foodbox.jar"]
EXPOSE 8081