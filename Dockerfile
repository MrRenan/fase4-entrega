# Use uma imagem base do Java
FROM openjdk:17-jdk-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR da aplicação para o container
COPY target/fase4-entrega-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta da aplicação
EXPOSE 8083

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]