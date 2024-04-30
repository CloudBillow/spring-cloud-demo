package com.alpharion.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RateLimitController
 *
 * @author XieYT
 * @since 2024/04/30 下午7:12
 */
@SuppressWarnings("unused")
@Slf4j
@RestController
@RequestMapping("/sentinel/rate")
public class RateLimitController {

    /*
     * 案例一：不加@SentinelResource注解时，默认情况下，遇到熔断降级时返回：Blocked by Sentinel (flow limiting)
     */
    @GetMapping("/byUrl")
    public String byUrl() {
        return "按rest-url限流测试OK";
    }


    /**
     * 案例二：使用@SentinelResource注解
     * value：资源名称（需要唯一）
     * blockHandler：限流后的处理方法（需要再本类）
     */
    @SentinelResource(value = "myResource", blockHandler = "handleResource")
    @GetMapping("/byResource")
    public String byResource() {
        return "按资源名称限流测试OK";
    }

    public String handleResource(BlockException blockException) {
        return "资源限流啦~ (来自自定义的限流处理逻辑)";
    }


    /**
     * 案例三：使用@SentinelResource注解
     * value：资源名称
     * blockHandler：限流后的处理方法
     * fallback：降级后的处理方法
     * 注意：限流的优先级高于降级
     */
    @GetMapping("/doTest/{id}")
    @SentinelResource(value = "doTest", blockHandler = "blockHandler", fallback = "fallback")
    public String doTest(@PathVariable("id") Long id) {
        if (id == 0) {
            throw new RuntimeException("id不能为0");
        }
        return "doTest";
    }

    public String blockHandler(@PathVariable("id") Long id, BlockException e) {
        return "自定义的blockHandler";
    }

    public String fallback(@PathVariable("id") Long id, Throwable e) {
        return "自定义的fallback";
    }

}
