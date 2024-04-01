package com.alpharion.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常
 *
 * @author XieYT
 * @since 2024/03/24 23:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private Integer code;

    private String message;

    public BaseException(ReturnCODE codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
        this.message = codeEnum.getMessage();
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
