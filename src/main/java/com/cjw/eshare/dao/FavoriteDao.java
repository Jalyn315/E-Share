package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Favorite;
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
public interface FavoriteDao {

    //添加一个收藏
    @Insert("insert into favorite(user_id, file_id, create_time) value (#{user_id}, #{file_id}, #{create_time})")
    void createFavorite(Favorite favorite);

    //移除一个收藏
    @Delete("delete from favorite where user_id = #{user_id}")
    void deleteFavorite(Integer user_id);

    //查看当前用户的收藏
    @Select("select * from favorite where user_id = #{user_id}")
    List<Favorite> findAllByUserId(Integer user_id);

    //查看某个文件被收藏的次数
    @Select("select count(*) from favorite where file_id = #{user_id}")
    Integer findCountByFileId(Integer file_id);
}
