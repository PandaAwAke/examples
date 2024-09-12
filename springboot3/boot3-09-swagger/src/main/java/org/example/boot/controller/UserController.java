package org.example.boot.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.boot.entity.UserEntity;
import org.example.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "用户", description = "用户 CRUD")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public UserEntity getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @Operation(summary = "查询", description = "获取所有用户")
    @GetMapping("/users")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public String addUser(@Parameter(name = "用户") @RequestBody UserEntity user) {
        userService.addUser(user);
        return "ok";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "ok";
    }

}
