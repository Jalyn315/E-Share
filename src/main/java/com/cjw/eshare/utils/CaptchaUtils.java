package com.cjw.eshare.utils;

import com.cjw.eshare.model.CRModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cj.w
 * @program: eshare
 * @description: 验证码工具
 * @create: 2021/3/3 22:48
 */
public class CaptchaUtils {

    /**
     * 判断验证码是否正确
     * @param code
     * @param request
     * @return
     */
    public static boolean verifyCaptcha(String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if ("".equals(captcha) || !captcha.equalsIgnoreCase(code)) {
            return false;
        }
        return  true;
    }
}
