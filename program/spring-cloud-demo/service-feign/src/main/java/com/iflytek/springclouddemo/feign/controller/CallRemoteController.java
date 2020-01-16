package com.iflytek.springclouddemo.feign.controller;

import com.iflytek.springclouddemo.feign.service.CallRemoteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * demo Controller
 * 2019/9/30 11:20
 *
 * @author DuHuang
 */
@RestController
public class CallRemoteController {
    @Resource
    private CallRemoteService callRemoteService;

    @RequestMapping("/callRemote")
    public String callRemoteService() {
        return callRemoteService.sayHelloCallByRemote("duhuang","Hi,boy!");
    }
}
