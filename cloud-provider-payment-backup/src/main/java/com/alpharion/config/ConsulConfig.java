package com.alpharion.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Consul配置
 *
 * @author XieYT
 * @since 2024/03/25 12:58
 */
@Getter
@Configuration
@RefreshScope
public class ConsulConfig {

    @Value("${alpharion.info}")
    private String alpharionInfo;
}
