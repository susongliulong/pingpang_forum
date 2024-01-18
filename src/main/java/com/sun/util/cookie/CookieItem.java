package com.sun.util.cookie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 二次封装cookie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookieItem {

    private String value;// cookie的value值或value为空时的提示信息
    private CookieState cookieState;// cookieState表明value值为0的情形
}
