package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.FileConfigConstant;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.FileDao;
import com.cjw.eshare.entity.Download;
import com.cjw.eshare.entity.ResourceFile;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.service.*;
import com.cjw.eshare.utils.FileUtils;
import com.sun.org.apache.bcel.internal.classfile.SourceFile;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:12
 */
@Service
public class FileService implements IFileService {

    @Autowired
    private FileDao fileDao;
    @Autowired
    private IDownloadService downloadService;
    @Autowired
    private IUserService userService;
    @Autowired
    IUploadService uploadService;
    @Autowired
    IFavoriteService favoriteService;



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
            newFile.setFilename(UUID_FileName);
            //判断文件是否可以上传  大小不超过限定大小      上传文件不为空

            boolean isUploaded = newFile.getFile_size() < FileConfigConstant.MAX_SIZE && !file.isEmpty();
            if (isUploaded) {
                try {
                    file.transferTo(new java.io.File(fileSaveUrl + UUID_FileName));  //文件上传
                    //创建文件实体类 从上传记录中获取相应的文件信息
                    //调用持久层接口 存入文件信息
                    fileDao.createFile(newFile);
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

    /**
     * 下载文件
     * @param id
     * @param response
     * @return
     */
    @Override
    @Transactional
    public CRModel downloadFile(Integer id, HttpServletResponse response) {
        ResourceFile file = fileDao.findFileById(id);
        //获取文件名称
        String fileName = file.getFilename();
        System.out.println(fileName);
        //获取文件路径
        String filePath = file.getFile_url();
        //获取要上传的文件实体
        java.io.File file1 = new java.io.File(filePath + fileName);
        System.out.println("文件所在地址" + filePath + fileName);

        if (!file1.exists()) {
            System.out.println("文件已经删除");
            return CRModel.error(ErrorDescription.DOWNLOAD_FILE_ERR);
        } else if (file.getIs_download() == 0) {
            System.out.println("对不起您没有权限下载该文件");
            return CRModel.error(ErrorDescription.DOWNLOAD_FILE_ERR1);
        }
        Download download = new Download();
        download.setFile_id(file.getId());
        download.setUser_id(userService.getCurrentUserId());
        download.setDownload_at(new Date());

        response.setContentType("application/gorce-download");
        response.addHeader("Content-disposition", "attachment;fileName=" + FileUtils.getFileRealName(fileName));
        try {
            InputStream in = new FileInputStream(file1);
            OutputStream out = response.getOutputStream();
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            in.close();
            out.close();
            fileDao.updateFileDownloadAmount(id, file.getDownload_amount() + 1); //更新下载次数
            downloadService.createDownload(download);  //创建下载记录
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }

    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    @Override
    public CRModel getFileById(Integer id) {
        ResourceFile file = fileDao.findFileById(id);
        if (null != file) {
            return CRModel.success("", file);
        } else {
            return CRModel.error(ErrorDescription.GET_FILE_ERROR);
        }
    }

    /**
     * 获取所有文件
     * @return
     */
    @Override
    public List<ResourceFile> getAllFiles() {
        List<ResourceFile> files = fileDao.listFiles();
        if (null != files) {
            return files;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 根据用户id获取文件
     * @param user_id
     * @return
     */
    @Override
    public List<ResourceFile> getFileByUserId(Integer user_id) {
        List<ResourceFile> files = fileDao.findFileByUserId(user_id);
        if (null != files) {
            return files;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 更新文件权限
     * @param id
     * @param is_show
     * @param is_download
     * @return
     */
    @Override
    public CRModel updateFilePermission(Integer id, Integer is_show, Integer is_download) {
        ResourceFile file = fileDao.findFileById(id);
        if (null == file) {
            return CRModel.error(ErrorDescription.UPDATE_FILE_ERR);
        } else {
            fileDao.updateFilePermission(id, is_show, is_download);
            return CRModel.success(SuccessDescription.UPDATE_FILE_SUCCESS);
        }
    }

    /**
     * 根据用户id删除文件
     * @param id
     * @return
     */
    @Override
    @Transactional
    public CRModel deleteFileById(Integer id) {

        ResourceFile file = fileDao.findFileById(id);
        File file1 = null;
        if (null == file || !( file1 = new File(file.getFile_url() + file.getFilename())).exists()) {
            return CRModel.error(ErrorDescription.DEL_FILE_ERR);
        }
        uploadService.deleteByFileId(id);
        //TODO 删除下载记录表的信息
//        downloadService.deleteByFileId(id);
        //TODO 删除收藏表收藏的文件
//        favoriteService.deleteByFileId(id);
        fileDao.deleteById(id); //删除数据库文件信息
        file1.delete(); //删除本地文件
        return CRModel.success(SuccessDescription.DEL_FILE_SUCCESS);
    }
}
