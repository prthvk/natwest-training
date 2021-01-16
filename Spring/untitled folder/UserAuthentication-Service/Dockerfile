# From openjdk:11-jre
# RUN mkdir usr/ReminderService
# WORKDIR usr/authservice
# ENV MYSQL_DATABASE=userdb
# ENV MYSQL_USER=root
# ENV MYSQL_PASSWORD=root
# ENV MYSQL_HOST=localhost
# COPY ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar UserAuthentication-Service.jar
# ENTRYPOINT ["java","-jar",UserAuthentication-Service.jar]

FROM openjdk:8-jre
WORKDIR usr/lib
ENV MYSQL_HOST=localhost
ENV MYSQL_DATABASE=keepnote
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
COPY ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthentication-Service.jar
ENTRYPOINT ["java", "-jar","UserAuthentication-Service.jar"]