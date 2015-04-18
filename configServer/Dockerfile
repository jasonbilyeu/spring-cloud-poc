FROM dockerfile/java:oracle-java7

MAINTAINER: jasonbilyeu@ymail.com

WORKDIR /data

COPY build/libs/config-server-0.0.1.jar /data/config-server-0.0.1.jar

ENTRYPOINT java -jar /data/config-server-0.0.1.jar