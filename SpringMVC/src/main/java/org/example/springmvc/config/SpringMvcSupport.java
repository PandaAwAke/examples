package org.example.springmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 访问 /pages/xxx 时走静态资源路径 /pages
        registry.addResourceHandler("/pages/**").addResourceLocations("/pages/");
        super.addResourceHandlers(registry);
    }

}
