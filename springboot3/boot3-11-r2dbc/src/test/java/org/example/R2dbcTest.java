package org.example;

import io.asyncer.r2dbc.mysql.MySqlConnectionConfiguration;
import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import org.example.entity.TAuthor;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class R2dbcTest {

    @Test
    public void testR2dbc() {
        //0、MySQL配置
        MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
                .host("localhost")
                .port(3306)
                .username("root")
                .password("123456")
                .database("test")
                .build();

        //1、获取连接工厂
//        MySqlConnectionFactory connectionFactory = ConnectionFactories.get("r2dbc:mysql://localhost:3306/test");
        MySqlConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration);

        //2、获取到连接，发送sql

        // JDBC： Statement： 封装sql的
        //3、数据发布者
        Mono.from(connectionFactory.create())
                .flatMapMany(connection -> connection
                                .createStatement("select * from t_author where id=?id and name=?name")
                                .bind("id",1L) //具名参数
                                .bind("name","张三")
                                .execute())
                .flatMap(result -> result.map(readable -> {
                    Long id = readable.get("id", Long.class);
                    String name = readable.get("name", String.class);
                    return new TAuthor(id, name);
                }))
                .subscribe(tAuthor -> System.out.println("tAuthor = " + tAuthor));
    }

}
