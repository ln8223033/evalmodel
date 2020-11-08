package com.ln.evalmodel.common;

/**
 * 错误定义
 *
 * @author huit
 * @date 09/07/2018
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    PARAM_ERROR(-2, "参数错误"),
    XSS_SQL_ERROR(-3, "包含非法字符"),
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    PERMISSION_DENIED_ERROR(403, "您没有足够的权限执行该操作!"),
    PHONE_ERROR(130, "手机号或登陆账号错误!"),
    LOGIN_CAPTCHA_ERROR(100, "验证码错误"),
    LOGIN_PWD_ERROR(101, "账号或密码错误"),
    NEED_GRAPH_CAPTCHA(102, "请输入图形验证码"),
    UPDATE_USED_PWD_ERROR(104, "原始密码错误"),
    LOGIN_NEED_ERROR(700, "您尚未登录或登录时间过长,请重新登录!"),
    PARAM_VALIDATE_ERROR(800, "参数校验错误!"),
    LOGIN_NO_ROLE(103, "该用户没有角色，无法登陆"),
    SUCCESS_SET_PWD(13, "登陆成功，未设置登陆密码"),
    SET_TX_CAPTCHA(12, "验证码未过期,请勿重复获取!"),
    INFO_NO_EXIST(1404, "数据不存在!"),
    PAY_PWD_ERROR(2, "支付密码错误!");
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
