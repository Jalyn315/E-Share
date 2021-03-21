package com.cjw.eshare.service;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UploadModel;
import org.springframework.web.multipart.MultipartFile;

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
}
