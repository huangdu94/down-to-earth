package com.iflytek.springclouddemo.feign.service;

import org.springframework.stereotype.Component;

/**
 * 断路后 回退操作
 * 2019/10/2 22:40
 *
 * @author DuHuang
 */
@Component
public class CallRemoteServiceHystric implements CallRemoteService {
    @Override
    public String sayHelloCallByRemote(String name, String message) {
        return String.format("Call fail.%s:%s",name,message);
    }
}
