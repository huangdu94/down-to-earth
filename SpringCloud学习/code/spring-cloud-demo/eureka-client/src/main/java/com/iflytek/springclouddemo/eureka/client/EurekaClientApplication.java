package com.iflytek.springclouddemo.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Eureka Client
 * 2019/9/29 0:16
 *
 * @author DuHuang
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {
    @Value("${server.port}")
    private String port;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @RequestMapping("/{name}")
    public String home(@PathVariable String name, @RequestParam(value = "message", defaultValue = "Hello World") String message) {
        return String.format("Come by port: %s,say to %s: %s.", port, name, message);
    }
}
