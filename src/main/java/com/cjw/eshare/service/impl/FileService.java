package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.FileConfigConstant;
import com.cjw.eshare.dao.FileDao;
import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.service.IFileService;
import com.cjw.eshare.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author cj.w
 * @date 2021/1/6 23:12
 */
@Service
public class FileService implements IFileService {

    @Autowired
    private FileDao fileDao;

//TODO 数量文件名称为UUID唯一性，对文件上传和更新数据库实现事务
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean uploadFile(MultipartFile file, ResourceFile newFile) {
        if (newFile != null) {
            //文件后缀
            String suffix = FileUtils.getFileSuffix(newFile.getFilename());
            //存储路径
            String storagePath = newFile.getFile_url();
            String UUID_FileName = FileUtils.getFileNameUUID(newFile.getFilename());
            //得到文件保存路径
            String fileSaveUrl = FileUtils.makePath(UUID_FileName, storagePath);
            System.out.println("路径=" + fileSaveUrl);
            newFile.setFile_url(fileSaveUrl);
            //判断文件是否可以上传  大小不超过限定大小      上传文件不为空

            boolean isUploaded = newFile.getFile_size() < FileConfigConstant.MAX_SIZE && !file.isEmpty();
            if (isUploaded) {
                try {
                    file.transferTo(new java.io.File(fileSaveUrl + UUID_FileName));  //文件上传
                    //创建文件实体类 从上传记录中获取相应的文件信息
                    //调用持久层接口 存入文件信息
                    fileDao.createFile(newFile);
                    int i = 10 / 0;
                } catch (Exception e) {
                    //如果发生了异常需要把已经上传成功的文件删除掉
                    File file1 = new File(fileSaveUrl + UUID_FileName);
                    if (file1.exists()) {    //文件是否已经被上传到本地
                        file1.delete();
                    }
                    e.printStackTrace();
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚事务
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer getLastInsertFileId() {
        return fileDao.findLastFileId();
    }
}
