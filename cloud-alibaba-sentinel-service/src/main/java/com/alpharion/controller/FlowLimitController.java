package com.alpharion.controller;

import com.alpharion.service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流控Controller
 *
 * @author XieYT
 * @since 2024/04/21 下午9:42
 */
@RestController
@RequestMapping("/sentinel/flow")
public class FlowLimitController {

    /**
     * 流控规则:
     * - 阈值类型：
     *   1.QPS：每秒钟的请求数
     *   2.线程数：每秒并发线程数（没有流控效果）
     * - 流控模式：
     *   1.直接：请求超过阈值时，直接限流
     *   2.关联：当关联的资源达到阈值时，就限流自己
     *   3.链路：对链路上指定的资源进行统计，如果达到阈值，就限流入口资源
     * - 流控效果：
     *   1.快速失败：超过QPS后直接失败，抛出异常
     *   2.预热（冷启动）：接到请求后，根据阈值逐渐升级，直到QPS
     *   3.排队：排队等待
     */

    @Resource
    private FlowLimitService flowLimitService;

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        return "------testB";
    }

    @GetMapping("/testC")
    public String testC() {
        flowLimitService.common();
        return "------testC";
    }

    @GetMapping("/testD")
    public String testD() {
        flowLimitService.common();
        return "------testD";
    }
}
