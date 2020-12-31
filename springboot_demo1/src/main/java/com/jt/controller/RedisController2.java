package com.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//当程序运行时加载指定资源
@PropertySource(value = "classpath:/properties/redis.properties",encoding = "utf-8")
public class RedisController2 {

    @Value("${redis.host2}") //spel spring EL表达式
    private String host;
    @Value("${redis.port2}")
    private Integer port;

    @RequestMapping("/getNode2")
    public String getNode() {
        return host + "|" + port;
    }
}
