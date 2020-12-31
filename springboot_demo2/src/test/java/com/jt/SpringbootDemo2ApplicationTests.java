package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootDemo2ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = new User();
        user.setAge(12)
                .setAge(12);
    }

    @Test
    void test01() {
        List<User> userList = userMapper.findAll();
        System.out.println(userList);
    }

    @Test
    void test02() {
        User user = new User();
        user.setName("zhangsan").setAge(15).setSex("男");
        userMapper.insert(user);
    }

    @Test
    void test03() {
        User user = userMapper.selectById(5);
        System.out.println(user);
    }

    @Test
    void test04() {
        List<User> userList = userMapper.selectList(null);
        System.out.println(userList);
    }

    @Test
    void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //常见的关系运算符 = eq, > gt, < lt, >= ge, <= le
        queryWrapper.eq("name", "唐僧");
        List<User> userList = userMapper.selectList(queryWrapper);//条件构造器
        System.out.println(userList);
    }

    @Test
    void test06() {
        Integer[] ids = {1, 3, 5, 6};
//        List<Integer> idList = Arrays.asList(ids);
        List list = new ArrayList();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        Object[] objects = list.toArray(ids);
        queryWrapper.in("id", objects);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);
    }

    @Test
    void test07() {
        Integer[] ids = {1, 3, 5, 6};
        List<Integer> idList = Arrays.asList(ids);
        List<User> userList = userMapper.selectBatchIds(idList);
        System.out.println(userList);
    }

    @Test
    void test08() {

    }

    @Test
    void test09() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "君")
                .or()
                .eq("sex", "女")
                .orderByDesc("id");
        System.out.println(userMapper.selectList(queryWrapper));
    }

    @Test
    void test10(){
        User user = new User();
        user.setName("测试环境");
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", "zhangsan");
        int update = userMapper.update(user, updateWrapper);
        System.out.println(update);
    }
}
