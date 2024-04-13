package com.alpharion.controller;

import com.alpharion.api.PaymentApi;
import com.alpharion.result.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 链路监控
 *
 * @author XieYT
 * @since 2024/04/13 下午4:15
 */
@RestController
@RequestMapping("/order/micrometer/pay")
public class OrderMicrometerController {

    @Resource
    private PaymentApi paymentApi;

    @GetMapping(value = "/{id}", name = "支付链路监控")
    public R<String> micrometer(@PathVariable("id") Integer id) {
        return paymentApi.micrometer(id);
    }
}
