package com.alpharion.controller;

import cn.hutool.core.date.DateUtil;
import com.alpharion.api.PaymentApi;
import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import com.alpharion.result.ReturnCODE;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单Controller
 *
 * @author XieYT
 * @since 2024/03/24 23:56
 */
@Slf4j
@RestController
@RequestMapping("/order/pay")
public class OrderController {


    @Resource
    private PaymentApi paymentApi;

    @RequestMapping(value = "/add", name = "新增订单")
    public R<?> add(@RequestBody PayDTO param) {
        return paymentApi.addPay(param);
    }

    @RequestMapping(value = "/update", name = "更新订单")
    public R<?> update(@RequestBody PayDTO param) {
        return paymentApi.updatePay(param);
    }

    @RequestMapping(value = "/get/{id}", name = "获取订单")
    public R<?> get(@PathVariable("id") Integer id) {
        R<PayDTO> pay;
        try {
            System.out.println("开始获取订单:" + DateUtil.now());
            pay = paymentApi.getPay(id);
        } catch (Exception e) {
            log.error("获取订单异常", e);
            return R.fail(ReturnCODE.RC500.getCode(), "获取订单异常");
        } finally {
            System.out.println("获取订单结束:" + DateUtil.now());
        }
        return pay;
    }

    @RequestMapping(value = "/delete/{id}", name = "删除订单")
    public R<?> delete(@PathVariable("id") Integer id) {
        return paymentApi.deletePay(id);
    }
}
