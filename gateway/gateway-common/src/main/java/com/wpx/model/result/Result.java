package com.wpx.model.result;

import com.wpx.exception.envm.AuthException;

import java.util.Objects;

public class Result {

    private String code;
    private String msg;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result error(AuthException authException) {
        if (Objects.isNull(authException)) {
            return error("Internal Server Error", "服务器内部错误");
        }
        return error(authException.getCode(), authException.getMsg());
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
