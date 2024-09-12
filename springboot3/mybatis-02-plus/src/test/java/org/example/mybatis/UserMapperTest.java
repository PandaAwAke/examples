package org.example.mybatis;

import org.example.mybatis.entity.UserEntity;
import org.example.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        UserEntity user = new UserEntity();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.saveUser(user);
    }

    @Test
    void testSelectById() {
        UserEntity user = userMapper.queryUserById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<UserEntity> users = userMapper.queryUserByIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        UserEntity user = new UserEntity();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateUser(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteUser(5L);
    }
}