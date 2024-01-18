package com.sun.util.cookie;

import com.sun.istack.internal.NotNull;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理cookie
 */
public class CookieUtil {

    private CookieUtil() {

    }

    /**
     * 设置Cookie
     * @param cookie
     * @param httpResponse
     */
    public static void setCookie(Cookie cookie, HttpServletResponse httpResponse){
        httpResponse.addCookie(cookie);
    }

    /**
     * 根据key值获取cookie中的value，当value为空的时候，返回提示信息
     * 在本项目中，当value为空的时候，说明用户尚未登录，返回信息提示用户登录
     * @param key
     * @param httpServletRequest
     * @return
     */
    public static CookieItem getCookieValue(@NotNull String key, HttpServletRequest httpServletRequest){

        for (Cookie cookie : httpServletRequest.getCookies()) {

            if(key.equals(cookie.getName())){
                return new CookieItem(cookie.getValue(),CookieState.SUCCESS);
            }
        }
        return new CookieItem(null,CookieState.LOGIN_EXPIRED);
    }

    /**
     * 根据key值删除Cookie
     * @param key
     * @param request
     * @return
     */
    public static boolean deleteCookie(@NotNull String key, HttpServletRequest request){
        for (Cookie cookie : request.getCookies()) {

            if(key.equals(cookie.getName())){
                cookie.setMaxAge(-1);
                return true;
            }
        }
        return false;
    }
}
