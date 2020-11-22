FROM openjdk:8
WORKDIR /app
EXPOSE 8080
VOLUME /app/test
COPY app app
COPY conf conf
COPY project/build.properties project/build.properties
COPY project/plugins.sbt project/plugins.sbt
COPY build.sbt .
CMD ["sbt", "test"]

