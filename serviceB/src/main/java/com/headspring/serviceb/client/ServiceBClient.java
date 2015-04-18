package com.headspring.serviceb.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("serviceB")
public interface ServiceBClient {

    @RequestMapping(method = RequestMethod.GET, value = "/serviceBFeign")
    String serviceBFeign();
}
