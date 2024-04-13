package com.alpharion.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 返回码
 *
 * @author XieYT
 * @since 2024/03/24 22:50
 */
@Getter
@AllArgsConstructor
public enum ReturnCODE {

    /**
     * 成功
     */
    RC200(200, "成功"),

    /**
     * 失败
     */
    RC500(500, "失败"),

    /**
     * Token无效
     */
    TOKEN_INVALID(2001, "Token无效"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_FOUND(2002, "资源不存在"),

    ;

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 消息
     */
    private final String message;

    /**
     * 遍历
     */
    public static ReturnCODE getByCode(Integer code) {
        return Arrays.stream(ReturnCODE.values())
                .filter(e -> e.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
