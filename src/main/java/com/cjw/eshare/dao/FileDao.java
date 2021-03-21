package com.cjw.eshare.dao;

import com.cjw.eshare.entity.ResourceFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cj.w
 * @date 2020/12/31 2:14
 */
@Mapper
public interface FileDao {

    @Insert("insert into files(user_id,filename,type_id,file_size,file_url,description,is_show,is_download, praise_amount, download_amount)" +
            "value (#{user_id}, #{filename}, #{type_id}, #{file_size}, #{file_url}, #{description}, #{is_show}, #{is_download}, #{praise_amount}," +
            "#{download_amount})")
    void createFile(ResourceFile file);

    //删除文件
    @Delete("delete from files where id = #{id}")
    void deleteById(Integer id);

    //更新文件名称
    @Update("update files set filename = #{newFileName} where id = #{id}")
    void updateFileNameById(String newFileName, Integer id);

    //根据id查找文件
    @Select("select * from files where id = #{id}")
    ResourceFile findFileById(Integer id);

    //查询全部文件
    @Select("select * from files")
    List<ResourceFile> listFiles();

    //更改文件描述信息
    @Update("update files set description = #{newDescription} where id = #{id}")
    void updateFileDescription(Integer id, @Param("newDescription") String newDescription);

    //更新文件权限信息
    @Update("update files set is_show = #{is_show}, is_download = #{is_download} where id = #{id}")
    void updateFilePermission(@Param("id") Integer id, @Param("is_show") Integer is_show, @Param("is_download") Integer is_download);

    //更新点赞数量
    @Update("update files set praise_amount = #{newAmount} where id = #{id}")
    void updateFilePraiseAmount(Integer id, @Param("newAmount") Long newAmount);

    // 更新下载数量
    @Update("update files set download_amount = #{newAmount} where id = #{id}")
    void updateFileDownloadAmount(Integer id, @Param("newAmount") Long newAmount);

    @Select("select last_insert_id()")
    Integer findLastFileId();

    @Select("select * from files where user_id = #{user_id}")
    List<ResourceFile> findFileByUserId(@Param("user_id") Integer user_id);
}
