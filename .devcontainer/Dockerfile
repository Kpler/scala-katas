# See here for image contents: https://github.com/microsoft/vscode-dev-containers/tree/v0.234.0/containers/alpine/.devcontainer/base.Dockerfile

ARG VARIANT=3.15
FROM mcr.microsoft.com/vscode/devcontainers/base:0-alpine-${VARIANT}

ARG VSCODE_REMOTEUSER

RUN apk --no-cache add openjdk11

ENV PATH /sbt/bin:$PATH
ENV SBT_VERSION 1.6.2

RUN wget "https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz" && \
  tar -xzvf sbt-1.6.2.tgz
