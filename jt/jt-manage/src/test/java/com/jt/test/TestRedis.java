package com.jt.test;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

public class TestRedis {

    public Jedis getJedis(){
        String host = "192.168.126.129";
        int port = 6379;
        return new Jedis(host, port);
    }

    @Test
    public void testString(){
        Jedis jedis = getJedis();
        jedis.set("redis","你好Redis");
        System.out.println(jedis.get("redis"));
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
//        for (String key:keys) {
//            System.out.println(key);
//        }
    }

    @Test
    public void test01(){
        Jedis jedis = getJedis();
        if (jedis.exists("redis")){
            jedis.del("redis");
        } else {
            jedis.set("hello","你好redis");
            System.out.println(jedis.get("hello"));
        }
    }

    @Test
    public void test02(){
        Jedis jedis = getJedis();
        jedis.flushAll();
        jedis.set("redis","aaa");
        jedis.setnx("redis","bbb");
        System.out.println(jedis.get("redis"));
    }

    @Test
    public void test03() throws InterruptedException {
        Jedis jedis = getJedis();
        jedis.set("a","aaa");
//        jedis.expire("a",10);
        jedis.setex("a",10,"aaa");
        Thread.sleep(2000);
        System.out.println(jedis.ttl("a"));
    }

}
