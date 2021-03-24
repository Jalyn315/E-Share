package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.FileConfigConstant;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.UploadDao;
import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.entity.Type;
import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import com.cjw.eshare.service.IFileService;
import com.cjw.eshare.service.IUploadService;
import com.cjw.eshare.service.IUserService;
import com.cjw.eshare.utils.FileUtils;
import com.sun.org.apache.bcel.internal.generic.IUSHR;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:14
 */
@Service
public class UploadService implements IUploadService {

    @Autowired
    private IFileService fileService;
    @Autowired
    private IUserService userService;
    @Autowired
    private UploadDao uploadDao;

    /**
     * 上传文件
     * @param file
     * @param model
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CRModel uploadFile(MultipartFile file, UploadModel model) {

        ResourceFile newFile = new ResourceFile();
        Integer user_id = userService.getCurrentUserId();
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
            uploadInfo.setFile_id(fileService.getLastInsertFileId());
            uploadInfo.setUser_id(user_id);
            uploadDao.insertUpload(uploadInfo);
            return CRModel.success(SuccessDescription.UPLOAD_FILE_SUCCESS);
        }
        return CRModel.error(ErrorDescription.UPLOAD_FILE_ERR);
    }

    /**
     * 获取所有上传记录
     * @return
     */
    @Override
    public List<Upload> getAllUpload() {
        List<Upload> list = uploadDao.findAll();
        if (null != list) {
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * 或者指定用户上传的文件记录
     * @param id
     * @return
     */
    @Override
    public List<Upload> getUserUpload(Integer id) {
        List<Upload> list = uploadDao.findUploadByUserId(id);
        if (null != list) {
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public CRModel deleteByUserId(Integer user_id) {
        uploadDao.deleteByUserId(user_id);
        return CRModel.success(SuccessDescription.DEL_UPLOAD_SUCCESS);
    }

    @Override
    public CRModel deleteById(Integer id) {
        return uploadDao.deleteById(id) == 1
                ?CRModel.success(SuccessDescription.DEL_UPLOAD_SUCCESS)
                :CRModel.error(ErrorDescription.DEL_UPLOAD_ERR);
    }

    @Override
    public CRModel deleteByFileId(Integer file_id) {
        uploadDao.deleteByFileId(file_id);
        return CRModel.success(SuccessDescription.DEL_UPLOAD_SUCCESS);
    }
}
