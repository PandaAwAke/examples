package com.pandaawake.gateway.filters;


import cn.hutool.core.collection.CollUtil;
import com.pandaawake.common.exception.UnauthorizedException;
import com.pandaawake.gateway.config.AuthProperties;
import com.pandaawake.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        // 放行路径直接放行不需拦截
        // 这里不能直接 equals 比较而需要 antPathMatcher，因为配置文件中有通配符星号
        if (authProperties.getExcludePaths().stream()
                .anyMatch(pattern -> antPathMatcher.match(pattern, request.getPath().toString()))) {
            return chain.filter(exchange);
        }

        // 登录校验，获取 tokens
        String token = null;
        List<String> authHeaders = request.getHeaders().get("Authorization");
        if (CollUtil.isNotEmpty(authHeaders)) {
            token = authHeaders.get(0);
        }

        Long userId;
        try {
            userId = jwtTool.parseToken(token);    // 用 JwtTool 解析
        } catch (UnauthorizedException e) {
            // 拦截并设置响应码 401 未登录
            // 如果不用 try-catch，直接出错会返回 500 Bad Request 而不是未登录
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        // 传递用户信息
        // 网关将 userId 保存到请求头，然后转发给微服务
        // 微服务用 @RequestHeader(value = "user-info", required = false) 可以直接取到
        String userInfo = userId.toString();
        ServerWebExchange newExchange = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        return chain.filter(newExchange);  // 放行
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
