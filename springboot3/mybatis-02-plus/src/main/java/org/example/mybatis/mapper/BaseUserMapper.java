package org.example.mybatis.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.example.mybatis.entity.UserEntity;

public interface BaseUserMapper extends BaseMapper<UserEntity> {

    @Update("UPDATE user SET balance = balance - #{amount} ${ew.customSqlSegment}")
    void updateBalanceByIds(@Param("ew") LambdaQueryWrapper<UserEntity> wrapper,
                            @Param("amount") int amount);

}
