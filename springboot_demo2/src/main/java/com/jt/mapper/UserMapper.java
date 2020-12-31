package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {// 泛型必须写，通过泛型找到Class，获取Table注解上的表名
    List<User> findAll();   //查询全部的用户记录
}
