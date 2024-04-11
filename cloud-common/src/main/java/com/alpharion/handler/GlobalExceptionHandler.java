package com.alpharion.handler;

import cn.hutool.http.HttpUtil;
import com.alpharion.result.BaseException;
import com.alpharion.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeUnit;

/**
 * 全局异常拦截
 *
 * @author XieYT
 * @since 2024/03/24 23:02
 */
@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public R<?> baseExceptionHandler(BaseException e) {
        return R.fail(e.getCode(), e.getMessage());
    }

    public static void main(String[] args) {
        // 并发测试
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                String s = HttpUtil.get("http://localhost/order/circuit/pay/9999");
                System.out.println(s);
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        HttpUtil.get("http://localhost/order/circuit/pay/-4");


    }

//    @ExceptionHandler(Exception.class)
//    public R<?> exceptionHandler(Exception e) {
//        log.error("系统异常", e);
//        return R.fail(ReturnCODE.RC500.getCode(), e.getMessage());
//    }
}
