package com.alpharion.api;

import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 支付网关API
 *
 * @author XieYT
 * @since 2024/04/13 下午8:05
 */
@FeignClient("cloud-gateway")
public interface PaymentGatewayApi {

    @GetMapping(value = "/pay/gateway/get/{id}", name = "获取支付网关")
    R<PayDTO> getByGateway(@PathVariable("id") Integer id);

    @GetMapping(value = "/pay/gateway/info", name = "支付网关信息")
    R<String> infoByGateway();

}
