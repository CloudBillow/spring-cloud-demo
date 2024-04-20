package com.alpharion.config.predicate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义路由断言工厂（自定义会员等级）
 *
 * @author XieYT
 * @since 202/04/20 上午12:11
 */
@Component
public class GradeRoutePredicateFactory extends AbstractRoutePredicateFactory<GradeRoutePredicateFactory.Config> {

    /**
     * Grade key.
     */
    public static final String GRADE_LEVEL_KEY = "gradeLevel";

    public GradeRoutePredicateFactory() {
        super(GradeRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(GRADE_LEVEL_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(GradeRoutePredicateFactory.Config config) {
        return exchange -> {
            // 获取请求参数中的grade
            String gradeLevel = exchange.getRequest().getQueryParams().getFirst("gradeLevel");
            // 判断是否为空
            return config.getGradeLevel().equals(gradeLevel);
        };
    }

    @Data
    @Validated
    public static class Config {

        @NotNull
        private String gradeLevel;

    }
}
