package com.alpharion.controller;

import com.alpharion.api.PaymentApi;
import com.alpharion.result.R;
import com.alpharion.result.ReturnCODE;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单熔断Controller
 *
 * @author XieYT
 * @since 2024/04/11 下午8:47
 */
@RestController
@RequestMapping("/order/circuit/pay")
public class OrderCircuitController {

    @Resource
    private PaymentApi paymentApi;

    @GetMapping(value = "/{id}", name = "支付熔断")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public R<String> circuit(@PathVariable("id") Integer id) {
        return paymentApi.circuit(id);
    }

    /**
     * 服务降级方法
     */
    @SuppressWarnings("unused")
    public R<String> myCircuitFallback(Integer id, Throwable e) {
        return R.fail(ReturnCODE.RC500.getCode(), "系统繁忙请稍后重试：" + e.getMessage() + " id:" + id);
    }
}
