package org.example.mybatis.mapper;

import org.example.mybatis.entity.UserEntity;

import java.util.List;

public interface UserMapper {

    List<UserEntity> selectAll();

    UserEntity selectById(int id);

    int addUser(UserEntity user);

}
