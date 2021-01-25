package com.loohos.stategrid.common.result;

public enum ResultCode {
    SUCCESS(20000),
    FAIL(20001),
    LOGIN_SUCCESS(20000),
    LOGIN_ERROR(20001),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    public int code;

    ResultCode(int code) {
        this.code = code;
    }
}
