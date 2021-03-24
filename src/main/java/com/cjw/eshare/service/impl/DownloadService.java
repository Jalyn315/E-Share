package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.DownloadDao;
import com.cjw.eshare.entity.Download;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.IDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public CRModel getAllDownloadInfo() {
        List<Download> downloadList = null;
        if ((downloadList = downloadDao.findAllRecord()) != null) {
            return CRModel.success("",downloadList);
        } else {
            return CRModel.error(ErrorDescription.FIND_NO_DOWNLOAD_INFO);
        }
    }

    @Override
    public CRModel deleteDownloadById(Integer id) {
        return downloadDao.deleteDownloadRecordById(id) == 1
                ? CRModel.success(SuccessDescription.DEL_DOWNLOAD_SUCCESS)
                : CRModel.error(ErrorDescription.DEL_DOWNLOAD_ERR);
    }
}
