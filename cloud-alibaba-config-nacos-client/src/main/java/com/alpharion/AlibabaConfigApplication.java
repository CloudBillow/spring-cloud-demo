package com.alpharion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Alibaba Config Application
 *
 * @author XieYT
 * @since 2024/04/20 下午10:17
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaConfigApplication.class, args);
    }
}