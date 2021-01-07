package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //JSON转化为对象
    public static <T> T toObj(String json, Class<T> target) {
        try {
            return MAPPER.readValue(json, target);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String toJSON(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            //将检查异常，转化为运行时异常 之后被全局异常处理机制处理
            e.printStackTrace();//日志打印...
            throw new RuntimeException(e);
        }
    }
}
