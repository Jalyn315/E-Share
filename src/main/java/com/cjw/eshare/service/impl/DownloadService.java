package com.cjw.eshare.service.impl;

import com.cjw.eshare.dao.DownloadDao;
import com.cjw.eshare.entity.Download;
import com.cjw.eshare.service.IDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cj.w
 * @date 2021/1/6 23:11
 */
@Service
public class DownloadService implements IDownloadService {

    @Autowired
    private DownloadDao downloadDao;

    @Override
    public void createDownload(Download download) {
        downloadDao.createDownloadRecord(download);
    }
}
