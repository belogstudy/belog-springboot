FROM joengenduvel/jre17

RUN mkdir /app

WORKDIR /app

COPY velogProject-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar

CMD ["java", "-jar", "spring-boot-app.jar"]
