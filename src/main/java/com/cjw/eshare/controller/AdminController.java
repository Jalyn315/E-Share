package com.cjw.eshare.controller;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.LoginModel;
import com.cjw.eshare.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author cj.w
 * @date 2021/1/6 23:23
 */
@RestController
@Api(tags = "AdminController")
public class AdminController {


    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "登陆之后返回token")
    @PostMapping("/admin/login")
    public CRModel Login(@RequestBody  LoginModel adminLoginModel, HttpServletRequest request) {
        return adminService.login(adminLoginModel.getUsername(), adminLoginModel.getPassword(),adminLoginModel.getCode(),request);
    }


    @ApiOperation(value = "获取当前用户登陆的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName(); //获取用户名
        Admin admin = adminService.getAdminByUserName(username);
        admin.setAdmin_pass(null); //去掉密码
        return admin;
    }

    /**
     * 退出登陆
     * @return
     */
    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public CRModel logout() {
        return CRModel.success("注销成功！");
    }


}
