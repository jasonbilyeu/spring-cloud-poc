# spring-cloud-poc
POC for Spring Cloud

## Pre-requisites
- Install docker and compose: https://docs.docker.com/compose/install/ 

## To Run
1.  Run ./gradlew clean build

2.  Run docker-compose up in the spring-cloud-poc folder.  This will start the config server, the eureka server,
    and some instances of service A and service B.  You should be able to navigate to the following urls:

| Description | Link/Command |
| ------: | :----------- |
| Eureka home | http://localhost:8761/ |
| Eureka apps | http://localhost:8761/eureka/apps |
| Config server Service A Dev | http://localhost:8888/serviceA/dev |
| Service A dev1 | http://localhost:8080/ |
| Service A dev1 with Feign | http://localhost:8080/serviceBFeign |
| Service A dev2 | http://localhost:8180/ |
| Service A dev2 with Feign | http://localhost:8180/serviceBFeign |
| Service B dev1 | http://localhost:8380/ |
| Service B dev2 | http://localhost:8480/ |
| Service A Hystrix Dashboard | http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8081%2Fhystrix.stream |
| If serviceA-dev.properties has occasionally.fail=true | for ((i=1;i<=1000;i++)); do   curl -s "localhost:8080" > /dev/null; done |
| After config changes, post here for single refresh | http://localhost:8081/refresh |
| After config changes, post here for bus refresh | http://localhost:8081/bus/refresh |