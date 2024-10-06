FROM openjdk:17-jdk-slim

ENV GAT_SERV_PORT=8080
ENV JWT_KEY=jwtsecretkey
ENV ACC_SERV_URL=http://noted-account-service:8081
ENV NOT_SERV_URL=http://noted-notes-service:8082
ENV CLIENT_URL=http://localhost:5173

WORKDIR /app/noted

COPY target/noted-gateway-service.jar noted-gateway-service.jar

ENTRYPOINT ["java", "-jar", "noted-gateway-service.jar"]
