package org.example.boot;


import org.example.boot.listener.MyListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App07 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .main(App07.class)
                .sources(App07.class)
                .listeners(new MyListener())
                .run(args);


    }

}
