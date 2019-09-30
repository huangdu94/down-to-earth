package com.iflytek.springclouddemo.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * demo Service
 * @FeignClient("spring.application.name")
 * 接下来接口中定义的方法@RequestMapping和参数列表要和远程服务中的一致(方法名可以不同)
 * 2019/9/30 11:21
 *
 * @author DuHuang
 */
@FeignClient("eureka-client")
public interface CallRemoteService {
    @RequestMapping("/{name}")
    String sayHelloCallByRemote(@PathVariable String name, @RequestParam(value = "message", defaultValue = "Hello World") String message);
}
