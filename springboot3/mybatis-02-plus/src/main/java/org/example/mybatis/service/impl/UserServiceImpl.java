package org.example.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.mybatis.entity.UserEntity;
import org.example.mybatis.mapper.UserMapper;
import org.example.mybatis.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {
}
