package com.yxm.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yxm
 * @Date: 2020/12/24 17:21
 * @Emial: yxm1136656235@163.com
 * @Description:    返回对象封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;

    private String message;

    private T data;


    /**
     * 操作成功
     * @param data  返回数据
     * @return
     */
    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 操作成功
     * @param data  返回数据
     * @param message   自定义返回提示消息
     * @return
     */
    public static <T> CommonResult<T> success(T data,String message){
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 操作失败
     * @param data  返回数据
     * @return
     */
    public static <T> CommonResult<T> failed(T data){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),ResultCode.FAILED.getMessage(),data);
    }

    /**
     * 操作失败
     * @param data  返回数据
     * @param message   自定义返回提示消息
     * @return
     */
    public static <T> CommonResult<T> failed(T data,String message){
        return new CommonResult<T>(ResultCode.FAILED.getCode(),message,data);
    }

}
