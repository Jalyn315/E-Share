package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.FileConfigConstant;
import com.cjw.eshare.dao.UploadDao;
import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import com.cjw.eshare.service.IFileService;
import com.cjw.eshare.service.IUploadService;
import com.cjw.eshare.utils.FileUtils;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @author cj.w
 * @date 2021/1/6 23:14
 */
@Service
public class UploadService implements IUploadService {

    @Autowired
    private IFileService fileService;
    @Autowired
    private UploadDao uploadDao;

    @Override
    public CRModel uploadFile(MultipartFile file, UploadModel model) {

        ResourceFile newFile = new ResourceFile();
        Integer user_id = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        String filename = FileUtils.getFileName(file.getOriginalFilename());
        newFile.setFilename(filename);
        newFile.setDescription(model.getDescription());
        newFile.setDownload_amount((long) 0);
        newFile.setIs_download(model.getIs_download());
        newFile.setIs_show(model.getIs_show());
        newFile.setFile_url(FileConfigConstant.UPLOAD_PATH);
        newFile.setPraise_amount((long) 0);
        newFile.setUser_id(user_id);
        newFile.setType_id(model.getTypeId());
        newFile.setFile_size((double)file.getSize());

        if (fileService.uploadFile(file, newFile)) {
            Upload uploadInfo = new Upload();
            uploadInfo.setUpload_at(new Date());
            uploadInfo.setUser_id(fileService.getLastInsertFileId());

            return CRModel.success("上传成功");
        }
        return CRModel.error("上传失败!");
    }
}
