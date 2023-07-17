FROM openjdk:17

EXPOSE 5500

WORKDIR /app

COPY . .

ADD build/libs/money-transfer-service-0.0.1-SNAPSHOT.jar mymoneytransfer.jar

ENTRYPOINT "java" "-jar" "mymoneytransfer.jar"