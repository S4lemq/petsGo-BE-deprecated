FROM maven:3.8.5-openjdk-18
ARG JAR_FILE=.mvn/wrapper/maven-wrapper.jar
WORKDIR /app
COPY . /app
COPY ${JAR_FILE} app.jar
RUN mvn clean install
CMD mvn spring-boot:run
