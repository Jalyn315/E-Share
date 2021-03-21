package com.cjw.eshare.service.impl;

import com.cjw.eshare.config.security.JwtTokenUtil;
import com.cjw.eshare.constant.ErrorCode;
import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.LoggerPrefix;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.UserDao;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.entity.UserExtend;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.LoginModel;
import com.cjw.eshare.model.RegisterModel;
import com.cjw.eshare.service.IUserService;
import com.cjw.eshare.utils.CaptchaUtils;
import com.cjw.eshare.utils.MD5Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cj.w
 * @date 2021/1/6 23:14
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHead;
    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     *
     * @param model 登陆信息
     * @param request
     * @return
     */
    @Override
    public CRModel login(LoginModel model, HttpServletRequest request) {
        //判断验证码是否正确
        String captchaCode = (String)request.getSession().getAttribute("captcha");
        if (!captchaCode.equalsIgnoreCase(model.getCode())) { //忽略大小写
            return CRModel.error(ErrorCode.CAPTCHA_ERR, ErrorDescription.CAPTCHA_ERR);
        }

        //判断密码是否输入正确
        User userDetails = userDao.findUserByName(model.getUsername());
        if (null == userDetails || !userDetails.getPassword().equals(MD5Util.md5Encryption(model.getPassword()))) {
            return CRModel.error(ErrorCode.PWD_ERR, ErrorDescription.USERNAME_PWD_ERR);
        }

        //更新security中的token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //获取token
        Map<String, Object> tokenMap = new HashMap<>();
        String token = jwtTokenUtil.generateToken(userDetails);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CRModel.success("登陆成功",tokenMap);
    }

    @Override
    public CRModel registration(RegisterModel model, HttpServletRequest request) {


         //注册判断输入的验证码是否正确
        if (!CaptchaUtils.verifyCaptcha(model.getCode(), request)) {
            return CRModel.error(ErrorCode.CAPTCHA_ERR, "验证码输入不正确，请重新输入！");
        }
         //判断当前用户名是否已经注册
        if (userDao.countOfUsername(model.getUsername()) > 0) {
            return CRModel.error(ErrorCode.HAS_USERNAME_ERR, "该用户名已经被注册，请更换用户名");
        }

        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(MD5Util.md5Encryption(model.getPassword()));
        user.setCreate_at(new Date());
        user.setUpdate_at(new Date());
        userDao.insertUser(user);
        user.setPassword("**********");
        logger.info(LoggerPrefix.FINEST + "注册成功\nuserInfo:" + user);
        return CRModel.success("您已经注册成功，快去登陆吧！");
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public User getUserByUserName(String username) {
        return userDao.findUserByName(username);
    }

    /**
     *  根据用户id修改密码
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public CRModel changePassword(String oldPassword, String newPassword) {
        //先获取id
        Integer user_id = getUserId();
        //先判断用户输入的旧密码是否正确
        if (!MD5Util.md5Encryption(oldPassword).equals(userDao.findPasswordById(user_id))) {
            return CRModel.error(ErrorCode.PWD_ERR, ErrorDescription.CHANGE_PWD_ERR);
        }
        //获取修改的结果
        boolean result = userDao.changePassword(user_id, MD5Util.md5Encryption(newPassword));
        return result ? CRModel.success(SuccessDescription.CHANGE_PWD_SUCCESS) :
                CRModel.error(ErrorDescription.CHANGE_PWD_ERR1);
    }

    /**
     * 根据用户Id获取用户信息
     * @return
     */
    @Override
    public CRModel getUserInfo() {

        UserExtend userInfo = userDao.findUserInfoById(getUserId());
        if (null == userInfo) {
            return CRModel.success("", new UserExtend());
        }
        return CRModel.success("", userInfo);
    }


    /**
     * 获取当前登陆的用户的Id
     * @return
     */
    private Integer getUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    /**
     *  更新用户信息
     * @return
     * @param userInfo
     */

    @Override
    public CRModel updateUserInfo(UserExtend userInfo) {
        //判断数据中是否存在该用户信息
        Integer userId = getUserId();
        userInfo.setUser_id(userId);
        UserExtend info = userDao.findUserInfoById(userId);
        if (null == info) {
            userDao.insertUserInfo(userInfo);
        } else {
            userDao.updateUserInfoByUserId(userInfo);
        }
        return CRModel.success(SuccessDescription.UPDATE_USER_INFO_SUCCESS, userInfo);
    }

    /**
     * 获取当前登陆的用户id
     * @return
     */
    @Override
    public Integer getCurrentUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
