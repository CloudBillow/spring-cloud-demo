package com.alpharion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Spring Cloud Alibaba Provider Payment Main
 *
 * @author XieYT
 * @since 2024/04/20 下午6:57
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaPaymentApplication.class, args);
    }
}