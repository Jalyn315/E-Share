package com.cjw.eshare.service;

import com.cjw.eshare.entity.ResourceFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author cj.w
 * @date 2021/1/6 23:10
 */
public interface IFileService {
    boolean uploadFile(MultipartFile file, ResourceFile newFile);

    Integer getLastInsertFileId();
}
