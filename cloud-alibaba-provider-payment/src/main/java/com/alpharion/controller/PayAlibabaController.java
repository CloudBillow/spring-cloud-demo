package com.alpharion.controller;

import com.alpharion.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付宝支付控制器
 *
 * @author XieYT
 * @since 2024/04/20 下午7:27
 */
@Slf4j
@RestController
@RequestMapping("/alibaba/pay")
public class PayAlibabaController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/nacos/{id}", name = "Nacos测试接口")
    public R<String> getPayInfo(@PathVariable("id") Integer id) {
        return R.success("Alibaba Nacos registry, serverPort: " + serverPort + " id: " + id);
    }

}
