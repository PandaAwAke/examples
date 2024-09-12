package org.example.boot;


import lombok.extern.slf4j.Slf4j;
import org.example.boot.bean.Cat;
import org.example.boot.bean.Dog;
import org.example.boot.bean.Pig;
import org.example.boot.bean.User;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class App06 {

    public static void main(String[] args) {
//        SpringApplication.run(App06.class, args);

//        SpringApplication application = new SpringApplication(App06.class);
//        // 自定义设置
//        application.setBannerMode(Banner.Mode.OFF);
//
//        application.run(args);

        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .main(App06.class)
                .sources(App06.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

        try {
            Cat cat = context.getBean(Cat.class);
            log.info("cat: {}", cat);
        } catch (Exception e) {
        }

        try {
            Dog dog = context.getBean(Dog.class);
            log.info("dog: {}", dog);
        } catch (Exception e) {
        }

        try {
            Pig pig = context.getBean(Pig.class);
            log.info("pig: {}", pig);
        } catch (Exception e) {
        }

        try {
            User user = context.getBean(User.class);
            log.info("user: {}", user);
        } catch (Exception e) {
        }
    }

}
