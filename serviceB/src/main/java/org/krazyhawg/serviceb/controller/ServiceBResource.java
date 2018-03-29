package org.krazyhawg.serviceb.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class ServiceBResource {

    @Inject
    private DiscoveryClient discoveryClient;

    public String serviceAUrl() {
        ServiceInstance serviceA = discoveryClient.getInstances("serviceA").get(0);
        return serviceA.getUri().toString();
    }

    @RequestMapping("/")
    @HystrixCommand
    public String home() {
        return "Greetings from Service B!  Now pointing to ServiceA at the following url using manual service discovery:  " + serviceAUrl();
    }

    @HystrixCommand
    @RequestMapping("/serviceBFeign")
    public String serviceBFeign() {
        return "Greetings from Service B!  You got here by using the feign client, how clever of you!";
    }
}
