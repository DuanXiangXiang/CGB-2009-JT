package com.jt.test;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Set;

@SpringBootTest
public class TestRedis {

    @Autowired
    private Jedis jedis;

    @Test
    public void testRedis(){
        jedis.set("aa","整合成功");
        System.out.println(jedis.get("aa"));
    }

    public Jedis getJedis() {
        String host = "192.168.126.129";
        int port = 6379;
        return new Jedis(host, port);
    }

    @Test
    public void testString() {
        Jedis jedis = getJedis();
        jedis.set("redis", "你好Redis");
        System.out.println(jedis.get("redis"));
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
//        for (String key:keys) {
//            System.out.println(key);
//        }
    }

    @Test
    public void test01() {
        Jedis jedis = getJedis();
        if (jedis.exists("redis")) {
            jedis.del("redis");
        } else {
            jedis.set("hello", "你好redis");
            System.out.println(jedis.get("hello"));
        }
    }

    @Test
    public void test02() {
        Jedis jedis = getJedis();
        jedis.flushAll();
        jedis.set("redis", "aaa");
        jedis.setnx("redis", "bbb");
        System.out.println(jedis.get("redis"));
    }

    @Test
    public void test03() throws InterruptedException {
        Jedis jedis = getJedis();
        jedis.set("a", "aaa");
//        jedis.expire("a",10);
        jedis.setex("a", 10, "aaa");
        Thread.sleep(2000);
        System.out.println(jedis.ttl("a"));
    }

    @Test
    public void test04() {
        Jedis jedis = getJedis();
        SetParams setParams = new SetParams();
        setParams.nx().ex(10);
        jedis.set("b","xxxxx",setParams);
        System.out.println(jedis.get("b"));
    }

    @Test
    public void test05() {
        Jedis jedis = getJedis();
        jedis.hset("user","id","100");
        jedis.hset("user","name","user信息");
        jedis.hset("user","age","18");
        jedis.hset("user","sex","男");
        System.out.println(jedis.hgetAll("user"));
    }

    @Test
    public void test06() {
        Jedis jedis = getJedis();
        jedis.lpush("list","1","2","3","4","5");
        System.out.println(jedis.rpop("list"));
    }

}
