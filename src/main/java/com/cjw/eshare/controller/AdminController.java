package com.cjw.eshare.controller;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.LoginModel;
import com.cjw.eshare.model.UserModel;
import com.cjw.eshare.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 获取全部的用户信息
     */
    @ApiOperation(value="获取全部用户")
    @GetMapping("/get_all_user")
    public CRModel getAllUser() {return adminService.getAllUser();}

    /**
     * 根据id删除用户（需要传用户id）
     * @param user_id
     * @return
     */
    @ApiOperation(value="根据 id 查询用户")
    @GetMapping("/get_user/{user_id}")
    public CRModel getUser(@PathVariable("user_id") Integer user_id) {return adminService.getUser(user_id);}

    /**
     * 根据id修改用户密码（需要传用户id）
      * @param userModel
     * @return
     */
    @ApiOperation(value="根据 id 更新用户密码")
    @PutMapping("/update_user_password")
    public CRModel updateUserPasswordById(@RequestBody UserModel userModel) {return adminService.updateUserPasswordById(userModel);}

    /**
     * 根据id更新用户信息（需要传用户id）
     * @param userModel
     * @return
     */
    @ApiOperation(value="根据 id 更新用户信息")
    @PutMapping("/update_user_info")
    public CRModel updateUserInfo(@RequestBody UserModel userModel) {
        //涉及多表查询
        //需要返回更新后的结果
        return adminService.updateUserInfo(userModel);
    }

    //----------------------------以上有一些为自己写的业务-----------------------



    /**
     * 根据id删除文件上传信息
     * @param id
     * @return
     */
    @ApiOperation(value="根据 id 删除文件上传信息")
    @DeleteMapping("/delete_upload_info/{id}")
    public CRModel deleteUploadInfo(@PathVariable("id") Integer id){
        return adminService.deleteUploadInfoById(id);
    }


    /**
     * 获取所有下载信息
     * @return
     */
    @ApiOperation(value="获取所有下载信息")
    @GetMapping("/get_all_download_info")
    public CRModel getAllDownloadInfo(){
        return adminService.getAllDownloadInfo();
    }


    /**
     * 根据 id 删除文件下载信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据 id 删除文件下载信息")
    @DeleteMapping("/delete_file_info/{id}")
    public CRModel deleteFileInfoById(@PathVariable("id") Integer id) {
        return adminService.deleteFileInfoById(id);
    }

    /**
     * 获取所有类型信息
     * @return
     */
    @ApiOperation(value = "获取所有文件类型信息")
    @GetMapping("/get_all_type_info")
    public CRModel getAllTypeInfo(){
        return adminService.getAllTypeInfo();
    }
    //TODO 根据id删除一个类型，注意外键约束，


}
