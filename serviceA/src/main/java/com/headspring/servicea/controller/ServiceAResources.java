package com.headspring.servicea.controller;

import com.headspring.serviceb.client.ServiceBClient;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAResources {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServiceBClient serviceBClient;

    public String serviceBUrl() {
        InstanceInfo instanceInfo = discoveryClient.getNextServerFromEureka("serviceB", false);
        return instanceInfo.getHomePageUrl();
    }

    @RequestMapping("/")
    @HystrixCommand
    public String home() {
        return "Greetings from Service A!  Now pointing to ServiceB at the following url using manual service discovery:  " + serviceBUrl();
    }

    @RequestMapping("/serviceBFeign")
    @HystrixCommand
    public String serviceBFeign() {
        return serviceBClient.serviceBFeign();
    }
}
