package com.alpharion.api;

import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 支付API
 *
 * @author XieYT
 * @since 2024/03/25 15:07
 */
@FeignClient("cloud-payment-service")
public interface PaymentApi {

    @PostMapping(value = "/pay/add", name = "新增支付")
    R<String> addPay(@RequestBody PayDTO param);

    @PostMapping(value = "/pay/delete/{id}", name = "删除支付")
    R<String> deletePay(@PathVariable("id") Integer id);

    @PostMapping(value = "/pay/update", name = "更新支付")
    R<String> updatePay(@RequestBody PayDTO param);

    @PostMapping(value = "/pay/get/{id}", name = "获取支付")
    R<PayDTO> getPay(@PathVariable("id") Integer id);

    @PostMapping(value = "/pay/get/fail/{id}", name = "获取支付失败")
    R<PayDTO> getPayFail(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/circuit/{id}", name = "支付熔断")
    R<String> circuit(@PathVariable("id") Integer id);

}
