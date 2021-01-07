package com.jt.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.ItemDesc;
import com.jt.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestObjectMapper {

    @Test
    public void test01() throws JsonProcessingException {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(100L).setItemDesc("转化测试").setCreated(new Date());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(itemDesc);
        System.out.println(json);
        ItemDesc itemDesc1 = objectMapper.readValue(json, ItemDesc.class);
        System.out.println(itemDesc1);
    }

    @Test
    public void testList() throws JsonProcessingException {
        List<ItemDesc> list = new ArrayList<>();
        list.add(new ItemDesc().setItemId(100L).setItemDesc("案例1"));
        list.add(new ItemDesc().setItemId(100L).setItemDesc("案例2"));
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(list);
        System.out.println(json);
        List list1 = objectMapper.readValue(json,list.getClass());
        System.out.println(list1);
    }

    @Test
    public void testObject(){
        ItemDesc itemDesc = new ItemDesc().setItemId(100L).setItemDesc("aaaa");
        String json = ObjectMapperUtil.toJSON(itemDesc);
        System.out.println(json);

        ItemDesc itemDesc1 = ObjectMapperUtil.toObj(json, ItemDesc.class);
        System.out.println(itemDesc1);
    }
}
