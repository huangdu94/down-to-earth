package org.example.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
@RestController
@RequestMapping("")
public class UserController {

    @RequestMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }
}