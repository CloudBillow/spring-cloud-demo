package com.alpharion.controller;

import com.alpharion.config.ConsulConfig;
import com.alpharion.entity.Pay;
import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import com.alpharion.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 支付Controller
 *
 * @author XieYT
 * @since 2024/03/24 22:13
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @Resource
    private ConsulConfig consulConfig;

    @PostMapping(value = "/add", name = "新增支付")
    public R<String> addPay(@RequestBody Pay param) {
        int i = payService.add(param);
        return R.success("成功新增" + i + "条数据");
    }

    @PostMapping(value = "/delete/{id}", name = "删除支付")
    public R<String> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return R.success("成功删除" + i + "条数据");
    }

    @PostMapping(value = "/update", name = "更新支付")
    public R<String> updatePay(@RequestBody Pay param) {
        int i = payService.update(param);
        return R.success("成功更新" + i + "条数据");
    }

    @PostMapping(value = "/get/{id}", name = "获取支付")
    public R<PayDTO> getPay(@PathVariable("id") Integer id) {
        System.out.println(consulConfig.getAlpharionInfo());
        try {
            // 测试Feign的默认超时时间
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Pay pay = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay, payDTO);
        return R.success("成功获取数据", payDTO);
    }
}
