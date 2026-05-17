FROM maven:3.8-openjdk-11 AS builder

COPY . /usr/src/creedengo

WORKDIR /usr/src/creedengo
RUN ./tool_prepare-codenarc.sh
RUN ./tool_build.sh

FROM sonarqube:10.3.0-community
USER root
COPY --from=builder /usr/src/creedengo/lib/* /opt/sonarqube/extensions/plugins/

USER sonarqube
