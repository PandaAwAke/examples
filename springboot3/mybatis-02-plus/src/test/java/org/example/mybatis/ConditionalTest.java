package org.example.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.mybatis.entity.UserEntity;
import org.example.mybatis.mapper.BaseUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ConditionalTest {

    @Autowired
    BaseUserMapper userMapper;

    @Test
    public void testWhere1() {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);

        List<UserEntity> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testWhere2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setBalance(2000);
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<UserEntity>()
                .eq("username", "jack");

        userMapper.update(userEntity, wrapper);
    }

    @Test
    public void testUpdate1() {
        UpdateWrapper<UserEntity> wrapper = new UpdateWrapper<UserEntity>()
                .setSql("balance = balance + 200")
                .in("id", List.of(1L, 2L, 4L));

        userMapper.update(null, wrapper);
    }

    @Test
    public void testLambdaQueryWrapper() {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                .select(UserEntity::getId, UserEntity::getUsername)
                .like(UserEntity::getUsername, "o")
                .ge(UserEntity::getBalance, 1000);

        List<UserEntity> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void testComplex1() {
        List<Long> ids = List.of(1L, 2L, 4L);
        int amount = 200;
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<UserEntity>()
                .in(UserEntity::getId, ids);
        userMapper.updateBalanceByIds(wrapper, amount);
    }

}
