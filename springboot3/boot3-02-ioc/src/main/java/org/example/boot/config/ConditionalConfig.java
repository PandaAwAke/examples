package org.example.boot.config;

import org.example.boot.bean.Cat;
import org.example.boot.bean.Dog;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalConfig {

    @ConditionalOnClass(name = "com.alibaba.druid.FastsqlException")
    @Bean
    public Cat cat01() {
        return new Cat("cat");
    }

    @ConditionalOnBean(value = Cat.class)
    @Bean
    public Dog dog01() {
        return new Dog("dog");
    }

}
