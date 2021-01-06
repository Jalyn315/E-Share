package com.cjw.eshare.dao;

import com.cjw.eshare.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author cj.w
 * @date 2020/12/31 2:13
 */
@Mapper
public interface UserDao {

    @Insert("insert into users(username,avatar_url,phone,email,password,create_at,update_at,login_at) value " +
            "(#{username}, #{avatar_url}, #{phone}, #{email}, #{password}, #{create_at}, #{update_at}, #{login_at})")
    void createUser(User user);

    @Select("select * from users where id = #{id}")
    User findUserById(Integer id);

    @Delete("delete from users where id = #{id}")
    void deleteUserById(Integer id);

    @Update("update users set username=#{username}, avatar_url=#{avatar_url},phone=#{phone},email=#{email},password=#{password}," +
            "create_at=#{create_at},update_at=#{update_at},login_at=#{login_at} where id = #{id}")
    void updateUserById(User user);


}
