package com.alpharion.config.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 自定义条件过滤器
 *
 * @author XieYT
 * @since 2024/04/20 下午5:53
 */
@Slf4j
@Component
public class MustExistParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MustExistParamGatewayFilterFactory.Config> {

    public MustExistParamGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // 获取请求参数中的status
            ServerHttpRequest request = exchange.getRequest();
            System.out.println("进入自定义条件过滤器CustomXxxGatewayFilterFactory");
            System.out.println("请求参数：" + request.getQueryParams());
            System.out.println("配置参数：" + config.getParamName());
            if (request.getQueryParams().containsKey(config.getParamName())) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
            return exchange.getResponse().setComplete();
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("paramName");
    }

    @Data
    public static class Config {

        /**
         * 设定一个值，匹配后才可以访问
         */
        private String paramName;

    }
}
