package org.example.boot.config;


import com.alibaba.druid.FastsqlException;
import org.example.boot.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Import(FastsqlException.class)
@Configuration
public class UserConfig {

    @Bean
    public FastsqlException fastsqlException() {
        return new FastsqlException();
    }

    @Scope("prototype")
    @Bean("user01")
    public User user1() {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }

}
