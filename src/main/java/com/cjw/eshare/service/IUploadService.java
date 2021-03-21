package com.cjw.eshare.service;

import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:10
 */
public interface IUploadService {

    /**
     *上传文件
     * @param file
     * @param model
     * @return
     */
    CRModel uploadFile(MultipartFile file, UploadModel model);

    /**
     * 获取所有上传记录
     * @return
     */
    List<Upload> getAllUpload();

    /**
     * 根据用户id获取用户上传记录
     * @param id
     * @return
     */
    List<Upload> getUserUpload(Integer id);

    /**
     * 根据id删除记录
     * @param user_id
     * @return
     */
    CRModel deleteByUserId(Integer user_id);

    /**
     * 根据用户id删除记录
     * @param id
     * @return
     */
    CRModel deleteById(Integer id);

    /**
     * 根据文件id删除记录
     * @param file_id
     * @return
     */
    CRModel deleteByFileId(Integer file_id);
}
