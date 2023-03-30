ARG TAG=21.0.1

FROM quay.io/keycloak/keycloak:${TAG}

ENV BCRYPT_PLUGIN_VERSION 2.0.0

ENV KEYCLOAK_DIR /opt/keycloak
ENV KC_PROXY edge

LABEL maintainer="Stephane, Segning Lambou <selastlambou@gmail.com>"

USER 0

COPY target/keycloak-bcrypt-${BCRYPT_PLUGIN_VERSION}.jar $KEYCLOAK_DIR/providers/keycloak-bcrypt.jar

RUN $KEYCLOAK_DIR/bin/kc.sh build

USER 1000
