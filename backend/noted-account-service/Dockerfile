FROM openjdk:17-jdk-slim

ENV ACC_SERV_PORT=8081
ENV ACC_SERV_DB_URL=jdbc:postgresql://noted-account-db:5433/noted_account_db
ENV ACC_SERV_DB_USERNAME=postgres
ENV ACC_SERV_DB_PASSWORD=password1234
ENV ACC_SERV_DB_DRIVERCLASSNAME=org.postgresql.Driver
ENV ACC_SERV_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV JWT_KEY=jwtsecretkey
ENV JWT_EXP=3600000

WORKDIR /app/noted

COPY target/noted-account-service.jar noted-account-service.jar

ENTRYPOINT ["java", "-jar", "noted-account-service.jar"]
