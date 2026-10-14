FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests -Dmaven.war.plugin.version=3.3.2
EXPOSE 8080
CMD ["mvn", "exec:java"]
