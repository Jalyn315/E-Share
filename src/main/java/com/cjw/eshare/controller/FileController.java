package com.cjw.eshare.controller;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.IFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author cj.w
 * @date 2021/1/6 23:24
 */
@RestController
public class FileController {

    @Autowired
    private IFileService fileService;

    @ApiOperation(value = "下载文件")
    @GetMapping("/download/{id}")
    public CRModel downloadFile(@PathVariable("id") Integer id, HttpServletResponse response) {
        return fileService.downloadFile(id, response);
    }


}
