package com.cjw.eshare.controller;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import com.cjw.eshare.service.IUploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cj.w
 * @date 2021/1/6 23:25
 */
@RestController
public class UploadController {

    @Autowired
    private IUploadService uploadService;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload" , consumes = { "multipart/form-data" })
    public CRModel uploadFile( MultipartFile file, UploadModel model) {
        return uploadService.uploadFile(file, model);
    }

}
