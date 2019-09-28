package com.iflytek.springclouddemo.config.client.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Spring Cloud Config Client 属性值 从Server 获取
 * 2019/9/28 18:07
 *
 * @author DuHuang
 */
@Component
public class ConfigClientProperties {
    @Value("message")
    private String message;
    @Value("secret")
    private String secret;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
