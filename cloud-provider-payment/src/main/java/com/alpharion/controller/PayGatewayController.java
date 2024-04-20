package com.alpharion.controller;

import cn.hutool.core.util.IdUtil;
import com.alpharion.entity.Pay;
import com.alpharion.pojo.dto.PayDTO;
import com.alpharion.result.R;
import com.alpharion.result.ReturnCODE;
import com.alpharion.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;

/**
 * 支付网关Controller
 *
 * @author XieYT
 * @since 2024/04/13 下午7:30
 */
@RestController
@RequestMapping("/pay/gateway")
public class PayGatewayController {

    @Resource
    private PayService payService;

    @GetMapping(value = "/get/{id}", name = "获取支付网关")
    public R<PayDTO> get(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        if (pay == null) {
            return R.fail(ReturnCODE.RESOURCE_NOT_FOUND.getCode(), "未找到对应记录: " + id);
        }
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(pay, payDTO);
        return R.success(payDTO);
    }

    @GetMapping(value = "/info", name = "支付网关信息")
    public R<String> info() {
        return R.success("支付网关信息: " + IdUtil.simpleUUID().toUpperCase());
    }

    @GetMapping(value = "/filter", name = "支付网关过滤")
    public R<String> getGatewayFilter(HttpServletRequest request) {
        int space = 20;
        StringBuilder result = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        // 右对齐，左边填充空格
        String paddedRight = String.format("%-" + space + "s", "---请求头名称---");
        System.out.println(paddedRight + "---请求头值---");
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(String.format("%-" + space + "s", name) + value);
            if (name.contains("Miracle") || name.contains("miracle")) {
                result.append(name).append("=").append(value).append(" ");
            }
        }
        return R.success("GatewayFilter过滤器测试：" + result + " " + IdUtil.simpleUUID().toUpperCase());
    }
}
