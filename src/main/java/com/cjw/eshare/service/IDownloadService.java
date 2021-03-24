package com.cjw.eshare.service;

import com.cjw.eshare.entity.Download;
import com.cjw.eshare.model.CRModel;

/**
 * @author cj.w
 * @date 2021/1/6 23:07
 */
public interface IDownloadService {
    void createDownload(Download download);

    /**
     * 获取所有下载信息
     * @return
     */
    CRModel getAllDownloadInfo();

    /**
     * 根据 id 删除下载信息
     * @param id
     * @return
     */
    CRModel deleteDownloadById(Integer id);
}
