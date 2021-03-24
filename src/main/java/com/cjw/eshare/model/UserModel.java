package com.cjw.eshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
     * @author cj.w
     * @program: eshare
     * @description: 管理员实体类
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true) //打开链式编程
    @ApiModel(value = "UserModel对象",description = "")
    public class UserModel {
        /************ users 字段 ************/
        @ApiModelProperty(value = "用户id", required = true)
        private Integer id;
        //更新密码所需字段
        @ApiModelProperty(value = "用户新密码")
        private String newPassword;
        @ApiModelProperty(value = "用户旧密码")
        private String oldPassword;
        @ApiModelProperty(value = "用户手机号")
        private String phone;
        @ApiModelProperty(value = "用户邮箱")
        private String email;
        /************ user_extends 字段 ************/
//        @ApiModelProperty(value = "users_id")
//        private Integer userId;
        @ApiModelProperty(value = "用户真实名字")
        private String realName;
        @ApiModelProperty(value = "用户生日")
        private Date birthday;
        @ApiModelProperty(value = "用户性别")
        private String sex;
        @ApiModelProperty(value = "用户自定义描述")
        private String description;
}
