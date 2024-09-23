FROM openjdk:8-jre-slim

EXPOSE 7099
EXPOSE 8099

WORKDIR /

ARG SEATA_DOWNLOAD_URL="https://github.com/apache/incubator-seata/releases/download/v2.0.0/seata-server-2.0.0.tar.gz"
#ARG SEATA_DOWNLOAD_URL="https://gh.api.99988866.xyz/https://github.com/apache/incubator-seata/releases/download/v2.0.0/seata-server-2.0.0.tar.gz"

RUN apt update && apt install -y curl && \
    curl -L -o "seata-server.tar.gz" $SEATA_DOWNLOAD_URL && \
    tar -xzvf "seata-server.tar.gz" && \
    mv "/seata" "/seata-server" && \
    rm -rf "/seata-server/conf/" "/seata-server/logs/" && \
    rm "/seata-server.tar.gz"

ADD "./seata-conf.tar.gz" "/seata-server/conf/"

ENV LOADER_PATH="/seata-server/lib"
ENV TZ="Asia/Shanghai"

CMD ["bash","-c","/seata-server/bin/seata-server.sh && tail -f /dev/null"]
