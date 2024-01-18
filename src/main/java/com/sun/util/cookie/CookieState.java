package com.sun.util.cookie;

/**
 * cookie中value为0时，返回用户提示信息
 */
public enum CookieState {

    SUCCESS("成功",200),
    LOGIN_EXPIRED("尚未登录，先登录",301);
    String desc;
    int code;
    CookieState(String desc,int code) {
        this.desc = desc;
        this.code = code;
    }
}
