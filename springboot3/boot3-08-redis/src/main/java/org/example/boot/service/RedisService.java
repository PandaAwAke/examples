package org.example.boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    public void testRedis() {
        redisTemplate.opsForValue().set("key", "test测试测试");
    }


}
