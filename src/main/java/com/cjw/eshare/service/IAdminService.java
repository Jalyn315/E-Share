package com.cjw.eshare.service;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.model.CRModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cj.w
 * @date 2021/1/6 23:06
 */
public interface IAdminService {
    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param captchaCode
     * @param request
     * @return
     */
    CRModel login(String username, String password, String captchaCode, HttpServletRequest request);

    /**
     * 根据用户获取用户
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);


}
