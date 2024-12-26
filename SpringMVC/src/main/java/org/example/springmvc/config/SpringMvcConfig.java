package org.example.springmvc.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"org.example.springmvc.controller", "org.example.springmvc.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
