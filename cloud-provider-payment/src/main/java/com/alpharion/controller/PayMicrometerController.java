package com.alpharion.controller;

import com.alpharion.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * 链路监控
 *
 * @author XieYT
 * @since 2024/04/13 下午4:10
 */
@RestController
@RequestMapping("/pay/micrometer")
public class PayMicrometerController {

    @GetMapping(value = "/{id}", name = "支付链路监控")
    public R<String> micrometer(@PathVariable("id") Integer id) {
        try {
            // 模拟支付链路监控时间
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return R.success("支付链路监控成功，id：" + id);
    }
}
