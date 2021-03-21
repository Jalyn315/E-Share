package com.cjw.eshare.controller;

import com.cjw.eshare.entity.Type;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.ITypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @ApiOperation(value = "获取类型列表")
    @GetMapping("/get_types")
    public List<Type> getTypes() {
        return typeService.getAllType();
    }

    @ApiOperation(value = "删除类型")
    @GetMapping("/del_type/{id}")
    public CRModel deleteType(@PathVariable("id") Integer id) {
        return typeService.deleteType(id);
    }

    @ApiOperation(value = "更新类型名称")
    @PostMapping("/update_type/{id}")
    public CRModel updateType(@PathVariable("id") Integer id, String newTypeName) {
        return typeService.updateType(id, newTypeName);
    }


}
