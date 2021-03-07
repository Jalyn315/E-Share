package com.cjw.eshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cj.w
 * @program: eshare
 * @description: 用户登陆实体类
 * @create: 2021/2/27 15:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true) //打开链式编程
@ApiModel(value = "LoginModel对象",description = "")
public class LoginModel {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;

}
