package org.example.boot;

import com.alibaba.druid.FastsqlException;
import org.example.boot.bean.Pig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class App02 {

    public static void main(String[] args) {
        var context = SpringApplication.run(App02.class, args);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }


        // ------------- UserConfig -------------
        // 多例，已经在 User 处设置 prototype
        Object user01 = context.getBean("user01");
        System.out.println(user01);

        // 引入外部
        System.out.println(Arrays.toString(context.getBeanNamesForType(FastsqlException.class)));


        // ------------- ConditionalConfig -------------
        // conditional config
        System.out.println(context.getBean("cat01"));
        System.out.println(context.getBean("dog01"));

        // ------------- ConfigurationProperties -------------
        Pig pig = context.getBean(Pig.class);
        System.out.println(pig);

    }

}
