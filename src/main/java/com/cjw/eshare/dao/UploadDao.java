package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.model.CRModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/4 23:30
 */
@Mapper
public interface UploadDao {
    @Insert("insert into uploads(user_id, file_id, upload_at) values (#{user_id}, #{file_id}, #{upload_at})")
    void insertUpload(Upload upload);

    @Select("select * from uploads")
    List<Upload> findAll();

    @Select("select * from uploads where user_id = #{user_id}")
    List<Upload> findUploadByUserId(@Param("user_id") Integer user_id);


    //TODO 对根据不同id实现对文件的删除

    CRModel deleteByUserId(Integer user_id);

    CRModel deleteById(Integer id);

    CRModel deleteByFileId(Integer file_id);
}
