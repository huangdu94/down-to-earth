package com.iflytek.springclouddemo.ribbon.controller;

import com.iflytek.springclouddemo.ribbon.service.CallRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo Controller
 * 2019/9/30 11:20
 *
 * @author DuHuang
 */
@RestController
public class CallRemoteController {
    @Autowired
    private CallRemoteService callRemoteService;

    @RequestMapping("/callRemote")
    public String callRemoteService() {
        return callRemoteService.sayHelloCallByRemote("duhuang");
    }
}
