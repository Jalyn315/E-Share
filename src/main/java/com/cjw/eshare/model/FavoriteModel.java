package com.cjw.eshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author cj.w
 * @program: eshare
 * @description: 收藏夹实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true) //打开链式编程
@ApiModel(value = "FavoriteModel对象",description = "")
public class FavoriteModel {
    @ApiModelProperty(value = "用户id", required = true)
    private Integer userId;
    @ApiModelProperty(value = "收藏的文件id", required = true)
    private Integer fileId;
    @ApiModelProperty(value = "创建时间", required = true)
    private Date creatTime;
}
