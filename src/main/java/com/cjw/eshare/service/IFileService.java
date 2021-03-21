package com.cjw.eshare.service;

import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.model.CRModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author cj.w
 * @date 2021/1/6 23:10
 */
public interface IFileService {
    /**
     * 上传文件
     * @param file
     * @param newFile
     * @return
     */
    boolean uploadFile(MultipartFile file, ResourceFile newFile);

    /**
     * 获取新上传文件的id
     * @return
     */
    Integer getLastInsertFileId();

    /**
     * 下载文件
     * @param id
     * @param response
     * @return
     */
    CRModel downloadFile(Integer id, HttpServletResponse response);
}
