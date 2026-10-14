FROM maven:3.8.1-openjdk-11
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests
EXPOSE 8080
CMD ["mvn", "exec:java"]
