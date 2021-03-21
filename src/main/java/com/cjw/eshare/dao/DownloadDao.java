package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Download;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/4 23:29
 */
@Mapper
public interface DownloadDao {

    @Insert("insert into downloads(user_id,file_id,download_at) value (#{user_id}, #{file_id}, #{download_at})")
    void createDownloadRecord(Download download);

    @Delete("delete from downloads where id = #{id}")
    void deleteDownloadRecordById(Integer id);

    @Select("select * from downloads")
    List<Download> findAllRecord();
    //TODO 实现对下载记录的增删改查，与上传类似
    
}
