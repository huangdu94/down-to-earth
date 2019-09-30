package com.iflytek.springclouddemo.ribbon.service;

/**
 * demo Service
 * 2019/9/30 11:21
 *
 * @author DuHuang
 */
public interface CallRemoteService {
    /**
     * 调用远程的sayHello端点
     * @return
     */
    String sayHelloCallByRemote(String name);
}
