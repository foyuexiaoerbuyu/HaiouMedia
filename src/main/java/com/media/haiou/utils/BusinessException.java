package com.media.haiou.utils;

import java.io.IOException;

public class BusinessException extends RuntimeException {
    private int code;

    public BusinessException(String message) {
        this(400, message); // 默认400错误码
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String msg, IOException e) {
        super(msg, e);
    }

    public int getCode() {
        return code;
    }
}