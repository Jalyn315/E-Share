package com.cjw.eshare.service;

import com.cjw.eshare.entity.User;
import com.cjw.eshare.entity.UserExtend;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.LoginModel;
import com.cjw.eshare.model.RegisterModel;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cj.w
 * @date 2021/1/6 23:09
 */
public interface IUserService {

    /**
     /* *
     *
     * @param model 登陆信息
     * @param request
     * @return
     */
    CRModel login(LoginModel model, HttpServletRequest request);

    /**
     * 注册
     * @param model 用户登陆注册信息
     * @param request
     * @return
     */
    CRModel registration(RegisterModel model, HttpServletRequest request);

    /**
     * 更具用户名获取用户
     * @param username
     * @return
     */
    User getUserByUserName(String username);

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    CRModel changePassword(String oldPassword, String newPassword);

    /**
     *  获取用户信息
     * @return
     */
    CRModel getUserInfo();

    /**
     * 更新用户信息
     * @return
     * @param userInfo
     */
    CRModel updateUserInfo(UserExtend userInfo);
}
