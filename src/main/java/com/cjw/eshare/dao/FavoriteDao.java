package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Favorite;
import com.cjw.eshare.model.FavoriteModel;
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
    //返回值依据主键是否自增，自增则返回受影响的行数(1)，不是自增则可能返回0
    @Insert("insert into favorite(user_id, file_id, create_at) value (#{user_id}, #{file_id}, #{create_at})")
    Integer createFavorite(Favorite favorite);

    //移除一个收藏
    @Delete("delete from favorite where file_id = #{file_id}")
    Integer deleteFavorite(Integer file_id);

    //查看当前用户的收藏
    @Select("select * from favorite where user_id = #{user_id}")
    List<Favorite> findAllByUserId(Integer user_id);

    //查看某个文件被收藏的次数
    @Select("select count(*) from favorite where file_id = #{file_id}")
    Integer findCountByFileId(Integer file_id);
}
