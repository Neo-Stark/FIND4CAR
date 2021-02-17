FROM alpine
EXPOSE 8080
ARG SBT_VERSION=1.4.3
ENV SBT_HOME=/usr/local/sbt
VOLUME /app/test /app/app

WORKDIR /app/test
COPY conf conf
COPY project/build.properties project/plugins.sbt project/
COPY build.sbt .
RUN apk add --no-cache openjdk8 curl bash && mkdir $SBT_HOME && \
    curl -sL https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz | \
    tar -xz --strip-components=1 -C $SBT_HOME && ln -s $SBT_HOME/bin/sbt /usr/bin/ && \
    apk del curl && \
    addgroup -S testgroup && adduser -S testuser -G testgroup -s /bin/ash && \
    chown -R testuser .

RUN sbt sbtVersion
CMD ["sbt", "test"]