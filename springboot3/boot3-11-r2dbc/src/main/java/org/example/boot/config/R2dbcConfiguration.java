package org.example.boot.config;


import org.example.boot.config.converter.BookConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.MySqlDialect;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@Configuration
public class R2dbcConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public R2dbcCustomConversions conversions() {
        return R2dbcCustomConversions.of(MySqlDialect.INSTANCE, new BookConverter());
    }

}
