package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Upload;
import com.cjw.eshare.model.CRModel;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

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

    @Delete("delete from uploads where user_id = #{user_id}")
    void deleteByUserId(@Param("user_id") Integer user_id);

    @Delete("delete from uploads where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    @Delete("delete from uploads where file_id = #{file_id}")
    void deleteByFileId(@Param("file_id") Integer file_id);
}
