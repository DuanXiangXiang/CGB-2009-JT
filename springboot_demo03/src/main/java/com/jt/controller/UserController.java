package com.jt.controller;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserMapper mapper;
    @RequestMapping("/findAll")
    public String findUser(Model model){
        List<User> userList = mapper.selectList(null);
        model.addAttribute("userList", userList);
        return "userListAjax";
    }

    @RequestMapping("/userAjax")
    @ResponseBody
    public List findUserAjax(){
        List<User> userList = mapper.selectList(null);
        return userList;
    }
}
