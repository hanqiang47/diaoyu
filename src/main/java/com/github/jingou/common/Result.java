package com.github.jingou.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author caedmon
 */
@ApiModel(value = "返回结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -5867346802355037166L;
    @ApiModelProperty(value = "操作CODE码")
    private Integer code;
    @ApiModelProperty(value = "操作MSG")
    private String msg;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result() {

    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> success() {
        return new Result<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = success();
        result.setData(data);
        return result;
    }
    public static <T> Result<T> error() {
        return new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),"操作失败");
    }

    public static <T> Result<T> error(T data) {
        Result<T> result = error();
        result.setData(data);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
