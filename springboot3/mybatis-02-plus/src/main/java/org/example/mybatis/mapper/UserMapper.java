package org.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.example.mybatis.entity.UserEntity;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {

    void saveUser(UserEntity user);

    void deleteUser(Long id);

    void updateUser(UserEntity user);

    UserEntity queryUserById(@Param("id") Long id);

    List<UserEntity> queryUserByIds(@Param("ids") List<Long> ids);

}
