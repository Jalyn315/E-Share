package com.cjw.eshare.service;

import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.model.CRModel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    CRModel getFileById(Integer id);

    /**
     * 获取所有文件
     * @return
     */
    List<ResourceFile> getAllFiles();

    /**
     * 根据用户id获取文件
     * @param user_id
     * @return
     */
    List<ResourceFile> getFileByUserId(Integer user_id);

    /**
     * 跟新文件权限
     * @param id
     * @param is_show
     * @param is_download
     * @return
     */
    CRModel updateFilePermission(Integer id, Integer is_show, Integer is_download);

    /**
     * 根据id删除文件
     * @param id
     * @return
     */
    CRModel deleteFileById(Integer id);
}
