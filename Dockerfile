FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/TCC-Java-Minha-Rede-Social-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENV DB_HOST=localhost
ENV DB_PORT=5433
ENV DB_NAME=orkut
ENV DB_USER=Orkut
ENV DB_PASSWORD=orkut
CMD java -jar TCC-Java-Minha-Rede-Social-0.0.1-SNAPSHOT.jar \
    --spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} \
    --spring.datasource.username=${DB_USER} \
    --spring.datasource.password=${DB_PASSWORD}