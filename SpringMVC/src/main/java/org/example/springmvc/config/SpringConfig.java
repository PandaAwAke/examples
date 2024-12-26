package org.example.springmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"org.example.springmvc.service", "org.example.springmvc.dao"})
public class SpringConfig {
}
