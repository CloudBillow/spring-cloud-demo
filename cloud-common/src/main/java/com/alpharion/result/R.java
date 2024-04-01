package com.alpharion.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一结果返回类
 *
 * @author XieYT
 * @since 2024/03/24 22:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> {

    /**
     * 消息
     */
    private String message;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 数据
     */
    private T data;

    /**
     * 时间戳
     */
    private long timestamp;

    public static <T> R<T> success(T data) {
        return R.<T>builder()
                .code(ReturnCODE.RC200.getCode())
                .message(ReturnCODE.RC200.getMessage())
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 自定义消息的成功返回
     */
    public static <T> R<T> success(String message, T data) {
        return R.<T>builder()
                .code(ReturnCODE.RC200.getCode())
                .message(message)
                .data(data)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    /**
     * 失败
     */
    public static <T> R<T> fail(Integer code, String message) {
        return R.<T>builder()
                .code(code)
                .message(message)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
