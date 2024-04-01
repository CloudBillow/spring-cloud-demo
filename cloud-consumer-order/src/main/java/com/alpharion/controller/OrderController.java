package com.alpharion.controller;

import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单Controller
 *
 * @author XieYT
 * @since 2024/03/24 23:56
 */
@RestController
@RequestMapping("/order/pay")
public class OrderController {

    // 支付服务的地址
    public static final String PAYMENT_SERVICE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/add", name = "新增订单")
    public R<?> add(@RequestBody PayDTO param) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/pay/add", param, R.class);
    }

    @RequestMapping(value = "/update", name = "更新订单")
    public R<?> update(@RequestBody PayDTO param) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/pay/update", param, R.class);
    }

    @RequestMapping(value = "/get/{id}", name = "获取订单")
    public R<?> get(@PathVariable("id") Integer id) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/pay/get/" + id, null, R.class);
    }

    @RequestMapping(value = "/delete/{id}", name = "删除订单")
    public R<?> delete(@PathVariable("id") Integer id) {
        return restTemplate.postForObject(PAYMENT_SERVICE_URL + "/pay/delete/" + id, null, R.class);
    }
}
