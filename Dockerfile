FROM alpine
WORKDIR /app
EXPOSE 8080
VOLUME /app/test
ARG SBT_VERSION=1.3.13
ENV SBT_HOME=/usr/local/sbt

COPY app app
COPY conf conf
COPY project/build.properties project/build.properties
COPY project/plugins.sbt project/plugins.sbt
COPY build.sbt .
RUN apk add --no-cache openjdk8 curl bash && mkdir $SBT_HOME && \
    curl -sL https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz | \
    tar -xz --strip-components=1 -C $SBT_HOME && ln -s $SBT_HOME/bin/sbt /usr/bin/ && \
    sbt sbtVersion
CMD ["sbt", "test"]

