package com.alpharion.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义全局过滤器
 *
 * @author XieYT
 * @since 2024/04/20 下午5:11
 */
@Slf4j
@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    /**
     * Process the Web request and (optionally) delegate to the next {@code GatewayFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 接口开始时间
        long startTime = System.currentTimeMillis();
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 接口结束时间
            long endTime = System.currentTimeMillis();
            // 计算接口耗时
            long executeTime = endTime - startTime;
            String sb = "接口" + exchange.getRequest().getURI().getHost() +
                    ":" +
                    exchange.getRequest().getURI().getPort() +
                    exchange.getRequest().getURI().getPath() +
                    "耗时：" +
                    executeTime +
                    "ms";
            log.info(sb);
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
