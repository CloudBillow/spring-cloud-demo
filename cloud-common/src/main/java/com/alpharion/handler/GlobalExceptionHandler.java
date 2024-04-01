package com.alpharion.handler;

import com.alpharion.result.BaseException;
import com.alpharion.result.R;
import com.alpharion.result.ReturnCODE;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

    @ExceptionHandler(Exception.class)
    public R<?> exceptionHandler(Exception e) {
        log.error("系统异常", e);
        return R.fail(ReturnCODE.RC500.getCode(), e.getMessage());
    }
}
