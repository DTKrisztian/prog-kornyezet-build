FROM openjdk:11

COPY "./target/factsite.jar" "/application/factsite.jar"

CMD ["java", "-jar", "/application/factsite.jar"]
