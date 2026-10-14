FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
RUN mvn clean package
EXPOSE 8080
CMD ["mvn", "exec:java"]
