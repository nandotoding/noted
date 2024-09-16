FROM openjdk:17-jdk-slim

ENV NOT_SERV_PORT=8082
ENV NOT_SERV_DB_URL=jdbc:postgresql://noted-notes-db:5434/noted_notes_db
ENV NOT_SERV_DB_USERNAME=postgres
ENV NOT_SERV_DB_PASSWORD=password1234
ENV NOT_SERV_DB_DRIVERCLASSNAME=org.postgresql.Driver
ENV NOT_SERV_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

WORKDIR /app/noted

COPY target/noted-notes-service.jar noted-notes-service.jar

ENTRYPOINT ["java", "-jar", "noted-notes-service.jar"]