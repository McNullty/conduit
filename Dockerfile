FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/conduit.jar /conduit/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/conduit/app.jar"]
