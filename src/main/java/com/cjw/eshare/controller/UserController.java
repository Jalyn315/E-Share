package com.cjw.eshare.controller;

import com.cjw.eshare.entity.User;
import com.cjw.eshare.entity.UserExtend;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.LoginModel;
import com.cjw.eshare.model.RegisterModel;
import com.cjw.eshare.service.impl.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author cj.w
 * @date 2021/1/6 23:26
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param model，用户输入的信息
     * @param request
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public CRModel register(@RequestBody RegisterModel model, HttpServletRequest request) {
        return userService.registration(model, request);
    }


    @ApiOperation(value = "登陆")
    @PostMapping("/login")
    public CRModel login(@RequestBody LoginModel model, HttpServletRequest request) {
        return userService.login(model, request);
    }


    @ApiOperation(value = "修改密码")
    @PostMapping("/change_password")
    public CRModel changePassword(String oldPassword, String newPassword) {
        return userService.changePassword(oldPassword, newPassword);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/user_info")
    public CRModel getUserInfo() {
        return userService.getUserInfo();
    }

    @ApiOperation(value = "更改用户个人信息")
    @PostMapping("/update_user_info")
    public CRModel updateUserInfo(@RequestBody UserExtend userInfo) {
        return userService.updateUserInfo(userInfo);
    }


}
