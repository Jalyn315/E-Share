package com.cjw.eshare.constant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author cj.w
 * @program: eshare
 * @description: 错误码
 * @create: 2021/3/4 22:16
 */

@ApiModel(value = "错误码", description = "系统错误状态码")
public class ErrorCode {

    @ApiModelProperty(value = "账户已存在")
    public static final long HAS_USERNAME_ERR = 11;
    @ApiModelProperty(value = "没有权限访问")
    public static final long NO_PERMISSION_ERR = 21;
    @ApiModelProperty(value = "验证码错误")
    public static final long CAPTCHA_ERR = 97;
    @ApiModelProperty(value = "密码错误")
    public static final long PWD_ERR = 98;
    @ApiModelProperty(value = "未知错误")
    public static final long DEFAULT_ERR = 99;
}
