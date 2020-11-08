package com.ln.evalmodel.common;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author huit
 * @date 14/07/2018
 */
public class Result<T> {

    public static final String APPLICATION_JSON = "application/json;charset=UTF-8";
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体的内容.
     */
    private T data;

    public Result(ResultEnum resultEnum) {
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

    public T getData() {
        return data == null ? (T) new Object() : data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result error() {
        return new Result(ResultEnum.UNKONW_ERROR);
    }

    public static Result error(String msg) {
        return error(ResultEnum.UNKONW_ERROR.getCode(), msg);
    }

    public static Result error(Integer code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static Result ok(Object data) {
        Result r = new Result();
        r.code = ResultEnum.SUCCESS.getCode();
        r.msg = ResultEnum.SUCCESS.getMsg();
        r.data = data;
        return r;
    }

    public static Result of(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMsg());
    }

    public static Result of(ResultEnum resultEnum, Object data) {
        return new Result(resultEnum.getCode(), resultEnum.getMsg(), data);
    }

    public static Result ok() {
        return new Result(ResultEnum.SUCCESS);
    }

    public void writeResponse(HttpServletResponse response) {
        response.setContentType(APPLICATION_JSON);
        String json = JSON.toJSONString(this);
        try {
            byte[] data = json.getBytes(Charset.forName("UTF-8"));
            response.setContentLength(data.length);
            response.getOutputStream().write(data);
        } catch (IOException e) {
        }
    }
}
