package com.cjw.eshare.constant;

/**
 * @author cj.w
 * @program: eshare
 * @description: 错误描述
 * @create: 2021/3/7 15:56
 */
public class ErrorDescription {

    public static final String CAPTCHA_ERR = "验证码输入有误，请从新输入!";
    public static final String USERNAME_PWD_ERR = "用户名或者密码有误，请重新输入！";
    public static final String CHANGE_PWD_ERR = "修改密码失败，您输入的原密码有误！";
    public static final String CHANGE_PWD_ERR1 = "修改密码失败，请稍后在重试！";
    public static final String HAS_USER_ERR = "该用户名已经被注册，请更换用户名";
    /********************文件类型***************************/
    public static final String CREATE_TYPE_ERR = "创建类型失败，改类型已存在！";
    public static final String DEL_TYPE_ERR = "删除失败，类型不存在！";
    public static final String UPDATE_TYPE_ERR = "更新失败，类型不存在！";
    public static final String UPDATE_TYPE_ERR1  = "更新失败，类型名已经存在！";
    /*********************文件****************************/
    public static final String UPLOAD_FILE_ERR = "文件上传失败";
    public static final String DOWNLOAD_FILE_ERR = "文件下载失败，文件不存在或已被删除！";
    public static final String DOWNLOAD_FILE_ERR1 = "对不起，作者设置了权限，您无权下载该文件！";
    public static final String GET_FILE_ERROR = "文件不存在！" ;
    public static final String UPDATE_FILE_ERR = " 跟新失败，文件不存在!";
    public static final String DEL_FILE_ERR = "删除失败，文件已丢失，请刷新！";
    /*****************************用户收藏**************************************/
    public static final String FIND_NO_FAVORITE = "该用户下还没有收藏！";
    public static final String UPDATE_FAVORITE_ERR = "更新用户收藏失败！";
    public static final String DELETE_FAVORITE_ERR = "收藏删除失败！";
    /*****************************管理员操作用户**************************************/
    public static final String FIND_NO_USER = "查无用户！";
    public static final String UPDATE_USER_PASSWORD_ERR = "更新用户密码失败！";
    public static final String UPDATE_USER_INFO_ERR = "更新用户信息失败！";

    /*****************************上传**************************************/
    public static final String DEL_UPLOAD_ERR = "删除上传信息失败!";
    /*****************************下载**************************************/
    public static final String FIND_NO_DOWNLOAD_INFO = "无下载信息！";
    public static final String DEL_DOWNLOAD_ERR = "删除下载信息失败！";
}
