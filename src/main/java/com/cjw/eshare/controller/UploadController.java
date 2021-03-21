package com.cjw.eshare.controller;

import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import com.cjw.eshare.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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


    @ApiOperation(value = "获取所有上传记录")
    @GetMapping("/get_all_upload")
    public List<Upload> getAllUpload() {
        return uploadService.getAllUpload();
    }

    @ApiOperation(value = "根据用户id查询上传记录")
    @GetMapping("/get_user_upload/{id}")
    public List<Upload> getUserUpload(@PathVariable("id") Integer user_id) {
        return uploadService.getUserUpload(user_id);
    }

    @ApiOperation(value = "根据id删除文件上传记录")
    @GetMapping("/del_upload/{id}")
    public CRModel deleteUploadById(@PathVariable("id") Integer id) {
        return uploadService.deleteById(id);
    }

    @ApiOperation(value = "根据用户id删除文件上传记录")
    @GetMapping("/del_upload/{user_id}")
    public CRModel deleteUploadByUserId(@PathVariable("user_id") Integer user_id) {
        return uploadService.deleteByUserId(user_id);
    }

    @ApiOperation(value = "根据文件id删除文件上传记录")
    @GetMapping("/del_upload/{file_id}")
    public CRModel deleteUploadByFileId(@PathVariable("file_id") Integer file_id) {
        return uploadService.deleteByFileId(file_id);
    }


}
