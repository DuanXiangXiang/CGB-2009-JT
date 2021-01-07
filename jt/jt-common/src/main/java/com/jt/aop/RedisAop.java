package com.jt.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RedisAop {

    @Pointcut("bean(itemCatServiceImpl)")
    public void pointCut(){

    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){    //JoinPoint 连接点
        System.out.println("我是一个前置通知");
    }

}
