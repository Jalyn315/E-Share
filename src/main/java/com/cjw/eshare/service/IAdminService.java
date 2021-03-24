package com.cjw.eshare.service;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UserModel;

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

    /**
     * 管理员查看全部用户
     * @return
     */
    CRModel getAllUser();

    /**
     * 管理员根据用户 id 查询用户
     * @param user_id
     * @return
     */
    CRModel getUser(Integer user_id);

    /**
     * 更新用户密码
     * @param userModel
     * @return
     */
    CRModel updateUserPasswordById(UserModel userModel);

    /**
     * 更新用户信息
     * @param userModel
     * @return
     */
    CRModel updateUserInfo(UserModel userModel);

    /**
     * 获取所有类型信息
     * @return
     */
    CRModel getAllTypeInfo();

    /**
     * 根据 id 删除文件信息
     * @param id
     * @return
     */
    CRModel deleteFileInfoById(Integer id);

    /**
     * 获取所有下载信息
     * @return
     */
    CRModel getAllDownloadInfo();


    /**
     * 根据 id 删除文件上传信息
     * @param id
     * @return
     */
    CRModel deleteUploadInfoById(Integer id);
}
