package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data   //get/set/toString/equals/hashCode/
@Accessors(chain = true)    //实现链式加载
@AllArgsConstructor         //全参构造
@NoArgsConstructor          //无参构造
@TableName("user")  //1.关连表名
public class User {

    @TableId(type = IdType.AUTO) //主键自增
    private Integer id;
//    @TableField(value = "name")   //当列名和属性名一致时，或满足驼峰映射时可省略
    private String name;
//    @TableField(value = "age")
    private Integer age;
//    @TableField(value = "sex")
    private String sex;

/*
   lombok实现链式加载的原理
   public User setId(Integer id){
        this.id = id;
        return this;
    }

    public User setName(String name){
        this.name = name;
        return this;
    }

    public User setAge(Integer age){
        this.age = age;
        return this;
    }

    public User setSex(String sex){
        this.sex = sex;
        return this;
    }
*/
}
