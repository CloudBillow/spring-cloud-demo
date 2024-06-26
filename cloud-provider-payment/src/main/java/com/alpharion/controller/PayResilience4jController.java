package com.alpharion.controller;

import cn.hutool.core.util.IdUtil;
import com.alpharion.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 支付熔断Controller
 *
 * @author XieYT
 * @since 2024/04/11 下午8:47
 */
@RestController
@RequestMapping("/pay/resilience4j")
public class PayResilience4jController {

    @GetMapping(value = "/circuit/{id}", name = "支付熔断")
    public R<String> circuit(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        return R.success("Hello Circuit！id:" + id + " ==> " + IdUtil.simpleUUID());
    }

    @GetMapping(value = "/bulkhead/{id}", name = "支付隔离")
    public R<String> bulkhead(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        }
        return R.success("Hello Bulkhead！id:" + id + " ==> " + IdUtil.simpleUUID());
    }

    @GetMapping(value = "/rate-limit/{id}", name = "支付限流")
    public R<String> rateLimit(@PathVariable("id") Integer id) {
        return R.success("Hello RateLimit！id:" + id + " ==> " + IdUtil.simpleUUID());
    }
}
