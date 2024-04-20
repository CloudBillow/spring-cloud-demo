package com.alpharion.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单控制器
 *
 * @author XieYT
 * @since 2024/04/20 下午7:43
 */
@RequestMapping("/alibaba/order/pay")
@RestController
public class OrderNacosController {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @GetMapping(value = "/nacos/{id}", name = "Nacos测试接口")
    public String getPayInfo(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(serverUrl + "/alibaba/pay/nacos/" + id, String.class);
    }
}
