package com.yxm.common;

/**
 * @Author: yxm
 * @Date: 2020/12/24 17:26
 * @Emial: yxm1136656235@163.com
 * @Description:
 */
public enum ResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),

    ;
    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
