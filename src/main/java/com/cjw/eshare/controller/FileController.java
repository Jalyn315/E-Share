package com.cjw.eshare.controller;

import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.IFileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @ApiOperation(value = "根据id获取文件")
    @GetMapping("/get_file{id}")
    public CRModel getFile(@PathVariable("id") Integer id) {
        return fileService.getFileById(id);
    }


    @ApiOperation(value = "获取所有文件信息")
    @GetMapping("/get_all_files")
    public List<ResourceFile> getAllFiles() {
        return fileService.getAllFiles();
    }

    @ApiOperation(value = "根据用户id获取文件")
    @GetMapping("/get_files/{user_id}")
    public List<ResourceFile> getFilesByUserId(@PathVariable("user_id") Integer user_id) {
        return fileService.getFileByUserId(user_id);
    }

    @ApiOperation(value = "更新文件权限")
    @PostMapping("/update_file_permission")
    public CRModel updateFilePermission(Integer id, Integer is_show, Integer is_download) {
        return fileService.updateFilePermission(id, is_show, is_download);
    }

    @ApiOperation(value = "根据文件id删除")
    @GetMapping("/del_file/{id}")
    public CRModel deleteFileById(@PathVariable("id") Integer id) {
        return fileService.deleteFileById(id);
    }



}