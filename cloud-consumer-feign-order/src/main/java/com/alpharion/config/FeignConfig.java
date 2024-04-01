package com.alpharion.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Indexed;

/**
 * Feign配置
 *
 * @author XieYT
 * @since 2024/03/25 17:10
 */
@Configuration
@Indexed

public class FeignConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(100, 1, 3);
    }

    // 也可以使用配置文件的方式配置
    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}

