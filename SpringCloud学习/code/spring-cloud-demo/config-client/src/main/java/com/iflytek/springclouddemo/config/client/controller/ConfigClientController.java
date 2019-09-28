package com.iflytek.springclouddemo.config.client.controller;

import com.iflytek.springclouddemo.config.client.property.ConfigClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Cloud Config Client Controller
 * 2019/9/28 18:05
 *
 * @author DuHuang
 */
@RestController
public class ConfigClientController {
    @Autowired
    private ConfigClientProperties configClientProperties;

    @RequestMapping(value = "/show/{key}", method = RequestMethod.GET)
    public String getProperties(@PathVariable String key) {
        switch (key) {
            case "message":
                return configClientProperties.getMessage();
            case "secret":
                return configClientProperties.getSecret();
            default:
                return String.format("key '%s' is not exist.", key);
        }
    }
}
