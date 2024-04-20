package com.alpharion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author XieYT
 * @since 2024/04/20 下午7:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AlibabaOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaOrderApplication.class, args);
    }
}