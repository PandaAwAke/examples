package com.pandaawake.trade.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                                .title("黑马商城交易接口API")
                                .version("1.0")
                                .description("这是一个描述")
                                .termsOfService("http://doc.xiaominfo.com")
                                .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")))
//                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
//                .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
//                        .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")))
                ;
    }

}
