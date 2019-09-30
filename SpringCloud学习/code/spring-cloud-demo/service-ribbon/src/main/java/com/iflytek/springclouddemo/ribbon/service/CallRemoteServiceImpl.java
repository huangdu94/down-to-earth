package com.iflytek.springclouddemo.ribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * demo Service
 * 2019/9/30 11:26
 *
 * @author DuHuang
 */
@Service
public class CallRemoteServiceImpl implements CallRemoteService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String sayHelloCallByRemote(String name) {
        return restTemplate.getForObject("http://eureka-client/"+name,String.class);
    }
}
