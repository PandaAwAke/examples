package org.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mybatis.entity.UserEntity;
import org.example.mybatis.mapper.UserMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RawMyBatisTest {

    private static SqlSession sqlSession;

    private static UserMapper userMapper;


    @BeforeAll
    public static void prepareSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 获取 SqlSession 对象
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @AfterAll
    public static void closeSqlSession() {
        sqlSession.close();
    }

    @Test
    public void testSelectAll() {
        List<UserEntity> users = sqlSession.selectList("org.example.mybatis.mapper.UserMapper.selectAll");
        System.out.println(users);
    }

    @Test
    public void testMapper() {
        List<UserEntity> users = userMapper.selectAll();
        System.out.println(users);

        UserEntity user = userMapper.selectById(1);
        System.out.println("user = " + user);
    }

    @Test
    public void testInsert() {
        UserEntity user = new UserEntity(null, "user4", "123456",
                "男", "重庆", "Game Science");

        int ret = userMapper.addUser(user);
        System.out.println("ret = " + ret);
        System.out.println("user.getId() = " + user.getId());

        sqlSession.commit();
    }

}
