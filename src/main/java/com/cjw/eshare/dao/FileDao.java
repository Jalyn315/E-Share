package com.cjw.eshare.dao;

import com.cjw.eshare.entity.ResourceFile;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author cj.w
 * @date 2020/12/31 2:14
 */
public interface FileDao {

    @Insert("insert into files(user_id,filename,type_id,file_size,file_url,description,is_show,is_download, praise_amount, download_amount)" +
            "value (#{user_id}, #{filename}, #{type_id}, #{file_size}, #{file_url}, #{description}, #{is_show}, #{is_download}, #{praise_amount}" +
            "#{download_amount})")
    void createFile(ResourceFile file);

    //删除文件
    @Delete("delete from file where id = #{id}")
    void deleteById(Integer id);

    //更新文件名称
    @Update("update files set filename = #{newFileName} where id = #{id}")
    void updateFileNameById(String newFileName, Integer id);

    //根据id查找文件
    @Select("select * from files where id = #{id}")
    ResourceFile findFileById();

    //查询全部文件
    @Select("select * from files")
    List<ResourceFile> listFiles();

    //更改文件描述信息
    @Update("update files set description = #{newDescription} where id = #{id}")
    void updateFileDescription(Integer id, String newDescription);

    //更新文件权限信息
    @Update("update files set is_show = #{is_show}, is_download = #{is_download} where id = #{id}")
    void updateFilePermission(Integer id, Integer is_show, Integer is_download);

    //更新点赞数量
    @Update("update files set praise_amount = #{newAmount} where id = #{id}")
    void updateFilePraiseAmount(Integer id, Long newAmount);

    // 更新下载数量
    @Update("update files set download_amount = #{newAmount} where id = #{id}")
    void updateFileDownloadAmount(Integer id, Long newAmount);

    //

}
