package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;
import java.net.URI;

public class TestWebFlux {

    @Test
    public void testWebFluxServer() throws IOException {
        // Webflux
        //1、创建一个能处理Http请求的处理器。 参数：请求、响应； 返回值：Mono<Void>：代表处理完成的信号
        HttpHandler handler = (ServerHttpRequest request, ServerHttpResponse response) -> {
            URI uri = request.getURI();
            System.out.println(Thread.currentThread()+"请求进来："+uri);
            //编写请求处理的业务,给浏览器写一个内容 URL + "Hello~!"
//            response.getHeaders(); //获取响应头
//            response.getCookies(); //获取Cookie
//            response.getStatusCode(); //获取响应状态码；
//            response.bufferFactory(); //buffer工厂
//            response.writeWith() //把xxx写出去
//            response.setComplete(); //响应结束

            //数据的发布者：Mono<DataBuffer>、Flux<DataBuffer>

            //创建 响应数据的 DataBuffer
            DataBufferFactory factory = response.bufferFactory();

            //数据Buffer
            DataBuffer buffer = factory.wrap((uri + " ==> Hello!").getBytes());


            // 需要一个 DataBuffer 的发布者
            return response.writeWith(Mono.just(buffer));
        };

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);

        // 启动 Netty 服务器
        HttpServer.create()
                .host("localhost")
                .port(8080)
                .handle(adapter) // 用指定的处理器处理请求
                .bindNow(); // 现在就绑定

        System.out.println("服务器启动完成....监听8080，接受请求");
        System.in.read();
        System.out.println("服务器停止....");
    }

}
