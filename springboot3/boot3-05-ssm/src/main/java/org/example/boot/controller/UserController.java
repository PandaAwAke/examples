package org.example.boot.controller;

import org.example.boot.bean.TUser;
import org.example.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/user/{id}")
    public TUser getUser(@PathVariable("id") Long id) {
        TUser user = userMapper.getUserById(id);
        return user;    // 返回 json 数据
    }

}
