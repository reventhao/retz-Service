package com.amarsoft.retz.model;

import com.amarsoft.retz.enums.HttpStatusEnum;
import lombok.Builder;
import lombok.Data;

@Data
public class CommonResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public CommonResult() {
    }

    public CommonResult(HttpStatusEnum httpStatusEnum, T data) {
        this.code = httpStatusEnum.getCode();
        this.msg = httpStatusEnum.getMsg();
        this.data = data;
    }

    public CommonResult(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(HttpStatusEnum.SUCCESS, data);
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<>(HttpStatusEnum.SUCCESS, null);
    }

    public static <T> CommonResult<T> fail() {
        return new CommonResult<>(HttpStatusEnum.FAIL, null);
    }

    public static <T> CommonResult<T> fail(Exception e) {
        return new CommonResult<>(500, e.getMessage());
    }
}
