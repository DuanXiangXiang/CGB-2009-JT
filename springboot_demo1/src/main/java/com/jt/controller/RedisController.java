package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Value("${redis.host}") //spel spring EL表达式
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @RequestMapping("/getNode")
    public String getNode() {
        return host + "|" + port;
    }
}
