package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            //配置client_id
            .withClient("client")
            //配置client-secret
            .secret(passwordEncoder.encode("123123"))
            //配置访问token的有效期
            .accessTokenValiditySeconds(3600)
            //配置刷新token的有效期
            .refreshTokenValiditySeconds(864000)
            //配置redirect_uri，用于授权成功后跳转
            .redirectUris("http://www.baidu.com")
            //配置申请的权限范围
            .scopes("all")
            //配置grant_type，表示授权类型
            .authorizedGrantTypes("authorization_code");
    }
}
