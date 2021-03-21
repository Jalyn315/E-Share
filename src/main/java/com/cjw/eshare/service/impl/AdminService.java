package com.cjw.eshare.service.impl;

import com.cjw.eshare.config.security.JwtTokenUtil;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.AdminDao;
import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.IAdminService;
import com.cjw.eshare.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cj.w
 * @date 2021/1/6 23:11
 */
@Service
public class AdminService implements IAdminService {

//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHead;


    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param captchaCode
     * @param request
     * @return
     */
    @Override
    public CRModel login(String username, String password, String captchaCode, HttpServletRequest request) {

        String captcha = (String) request.getSession().getAttribute("captcha");
        if ("".equals(captcha) || !captcha.equalsIgnoreCase(captchaCode)) {
            return CRModel.error("验证码输入错误，请重新输入!");
        }
        //获取UserDetails
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        //判断是否存在该账户信息
//        if (null == userDetails) {
//            return CRModel.error("您输入的帐户名不存在!");
//        }
        //判断密码是否正确
        Admin userDetails = adminDao.getAdminByName(username);
        if (!MD5Util.md5Encryption(password).equals(userDetails.getPassword())) {
            System.out.println(MD5Util.md5Encryption(password) + "*****" + userDetails.getPassword());
            return CRModel.error("用户名或密码错误，请联系管理员");
        }

        //更新security登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //获取token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CRModel.success(SuccessDescription.LOGIN_SUCCESS,tokenMap);
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminDao.getAdminByName(username);
    }
}
