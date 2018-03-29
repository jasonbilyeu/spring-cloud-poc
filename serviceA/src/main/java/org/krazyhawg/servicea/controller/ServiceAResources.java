package org.krazyhawg.servicea.controller;

import org.krazyhawg.serviceb.client.ServiceBClient;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Random;

@RestController
public class ServiceAResources {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAResources.class);

    @Inject
    private DiscoveryClient discoveryClient;

    @Inject
    private ServiceBClient serviceBClient;

    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "doFallback")
    public String home(@Value("${occasionally.fail:false}") boolean occasionallyFail) {
        logger.warn("Occasionally Fail is set to " + occasionallyFail);

        if (occasionallyFail && new Random().nextInt(5) + 1 == 1) {
            throw new RuntimeException("Failed!");
        }

        return "Greetings from Service A!  Now pointing to ServiceB at the following url using manual service discovery:  " + serviceBUrl();
    }

    @RequestMapping("/serviceBFeign")
    @HystrixCommand
    public String serviceBFeign() {
        return serviceBClient.serviceBFeign();
    }

    public String doFallback(boolean occasionallyFail) {
        return "Had to fall back!";
    }

    private String serviceBUrl() {
        ServiceInstance serviceB = discoveryClient.getInstances("serviceB").get(0);
        return serviceB.getUri().toString();
    }
}
