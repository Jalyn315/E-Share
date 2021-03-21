package com.cjw.eshare.controller;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.ITypeService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cj.w
 * @date 2021/1/6 23:25
 */
@RestController
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @ApiOperation(value = "创建类型")
    @PostMapping("/add_type")
    public CRModel createType(String typeName) {
        return typeService.createType(typeName);
    }


}
