FROM openjdk:17-jdk-alpine
LABEL maintainer="akshentsev69@gmail.com"
WORKDIR /app
COPY target/*.jar app.jar
ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:testdb
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.h2.Driver
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]