FROM maven:3.8.6-openjdk-11 AS builder

COPY ../spring .

RUN mvn clean install -DskipTests

FROM tomcat:9

COPY --from=builder target/jsp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]
