package com.alpharion.controller;

import com.alpharion.api.PaymentGatewayApi;
import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 走网关的订单Controller
 *
 * @author XieYT
 * @since 2024/04/13 下午7:54
 */
@RestController
@RequestMapping("/order/gateway/pay")
public class OrderGatewayController {

    @Resource
    private PaymentGatewayApi paymentGatewayApi;

    @GetMapping(value = "/get/{id}", name = "获取支付网关")
    public R<PayDTO> get(@PathVariable("id") Integer id) {
        return paymentGatewayApi.getByGateway(id);
    }

    @GetMapping(value = "/info", name = "支付网关信息")
    public R<String> info() {
        return paymentGatewayApi.infoByGateway();
    }


}
