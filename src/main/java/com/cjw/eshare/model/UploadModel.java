package com.cjw.eshare.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cj.w
 * @program: eshare
 * @description: 文件上传数据模型
 * @create: 2021/3/10 15:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "文件上传信息", description = "")
public class UploadModel {

    @ApiModelProperty(value = "文件名")
    private String filename;
    @ApiModelProperty(value = "上传的用户昵称")
    private String username;
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;
    @ApiModelProperty(value = "文件描述", required = true)
    private String description;
    @ApiModelProperty(value = "是否共享", required = true)
    private Integer is_show;
    @ApiModelProperty(value = "是否可以下载", required = true)
    private Integer is_download;
    @ApiModelProperty(value = "文件分类ID", required = true)
    private Integer typeId;

}
