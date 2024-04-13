package com.alpharion.controller;

import cn.hutool.http.HttpUtil;
import com.alpharion.api.PaymentApi;
import com.alpharion.result.R;
import com.alpharion.result.ReturnCODE;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 订单熔断Controller
 *
 * @author XieYT
 * @since 2024/04/11 下午8:47
 */
@RestController
@RequestMapping("/order/resilience4j/pay")
public class OrderResilience4jController {

    @Resource
    private PaymentApi paymentApi;

    /* ===================================== 熔断 ===================================== */

    @GetMapping(value = "/circuit/{id}", name = "支付熔断")
    @CircuitBreaker(name = "cloud-payment-service", fallbackMethod = "myCircuitFallback")
    public R<String> circuit(@PathVariable("id") Integer id) {
        return paymentApi.circuit(id);
    }

    /**
     * 熔断服务降级方法
     */
    @SuppressWarnings("unused")
    public R<String> myCircuitFallback(Integer id, Throwable e) {
        return R.fail(ReturnCODE.RC500.getCode(), "[myCircuitFallback] 系统繁忙请稍后重试：" + e.getMessage() + " id:" + id);
    }


    /* ===================================== 隔离 ===================================== */

    @GetMapping(value = "/bulkhead/semaphore/{id}", name = "支付隔离-信号量")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
    public R<String> bulkheadSemaphore(@PathVariable("id") Integer id) {
        return paymentApi.bulkhead(id);
    }

    @GetMapping(value = "/bulkhead/thread-pool/{id}", name = "支付隔离-线程池")
    @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.THREADPOOL)
    public CompletableFuture<R<String>> bulkheadThreadPool(@PathVariable("id") Integer id) {
        String name = Thread.currentThread().getName();
        System.out.println("bulkheadThreadPool:" + name);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.supplyAsync(() -> paymentApi.bulkhead(id));
    }

    /**
     * 隔离服务降级方法
     */
    @SuppressWarnings("unused")
    public CompletableFuture<R<String>> myBulkheadFallback(Integer id, Throwable e) {
        return CompletableFuture.supplyAsync(() ->
                R.fail(ReturnCODE.RC500.getCode(), "[myBulkheadFallback] 系统繁忙请稍后重试：" + e.getMessage() + " id:" + id));
    }


    /* ===================================== 限流 ===================================== */

    @GetMapping(value = "/rate-limiter/{id}", name = "支付限流")
    @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimiterFallback")
    public R<String> rateLimiter(@PathVariable("id") Integer id) {
        return paymentApi.rateLimit(id);
    }

    /**
     * 限流服务降级方法
     */
    @SuppressWarnings("unused")
    public R<String> myRateLimiterFallback(Integer id, Throwable e) {
        return R.fail(ReturnCODE.RC500.getCode(), "[myRateLimiterFallback] 系统繁忙请稍后重试：" + e.getMessage() + " id:" + id);
    }


    public static void main(String[] args) {
        List<CompletableFuture<Void>> futures = IntStream.range(0, 5)
                .mapToObj(i -> CompletableFuture.runAsync(() -> {
                    String s = HttpUtil.get("http://localhost/order/resilience4j/pay/rate-limiter/1");
                    System.out.println("s = " + s);
                })).toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

}
