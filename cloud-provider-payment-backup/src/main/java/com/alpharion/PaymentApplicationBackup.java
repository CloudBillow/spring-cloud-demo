package com.alpharion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 订单支付服务启动类
 *
 * @author XieYT
 * @since 2024/03/24 21:43
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.alpharion.mapper")
public class PaymentApplicationBackup {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationBackup.class, args);
    }
}