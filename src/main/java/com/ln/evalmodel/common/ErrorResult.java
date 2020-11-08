package com.ln.evalmodel.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 业务异常封装
 *
 * @author huit
 * @date 14/07/2018
 */
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class ErrorResult {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;


    public ErrorResult(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
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

    public ErrorResult() {
    }

    public ErrorResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorResult error() {
        return new ErrorResult(ResultEnum.UNKONW_ERROR);
    }

    public static ErrorResult error(String msg) {
        return error(ResultEnum.UNKONW_ERROR.getCode(), msg);
    }

    public static ErrorResult error(Integer code, String msg) {
        ErrorResult r = new ErrorResult();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
