FROM adoptopenjdk/openjdk11:latest
RUN useradd --create-home rabbitmq-user
USER rabbitmq-user
WORKDIR /home/rabbitmq-user
ARG JAR_FILE=target/\*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/home/rabbitmq-user/app.jar"]