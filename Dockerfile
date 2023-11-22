FROM openjdk:17-alpine
WORKDIR /app
COPY *.jar .
CMD ["java", "-jar", "JavaCoffeeShop!!!.jar"]
EXPOSE 8083
