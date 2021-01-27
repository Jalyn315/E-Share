package com.cjw.eshare.dao;

import com.cjw.eshare.entity.User;
import com.cjw.eshare.entity.UserExtend;
import org.apache.ibatis.annotations.*;

/**
 * @author cj.w
 * @date 2020/12/31 2:13
 */
@Mapper
public interface UserDao {

    /**
     * 插入一个用户
     * @param user
     */
    @Insert("insert into users(username,avatar_url,phone,email,password,create_at,update_at,login_at) value " +
            "(#{username}, #{avatar_url}, #{phone}, #{email}, #{password}, #{create_at}, #{update_at}, #{login_at})")
    void insertUser( User user);

    /**
     *插入一个用户信息
     * @param userInfo
     */
    @Insert("insert into user_extends(user_id, realname, birthday, sex, description) value (#{user_id}, #{realname}, #{birthday}, #{sex}, #{description})")
    void insertUserInfo(UserExtend userInfo);

    /**
     *查询一个用户
     * @param id 用户id
     * @return 用户登陆信息
     */
    @Select("select * from users where id = #{id}")
    User findUserById(Integer id);

    /**
     *删除一个用户
     * @param id 用户id
     */
    @Delete("delete from users where id = #{id}")
    void deleteUserById(Integer id);

    /**
     * 更新一个用户登陆信息
     * @param user 用户信息实体类
     */
    @Update("update users set username=#{username}, avatar_url=#{avatar_url},phone=#{phone},email=#{email},password=#{password}," +
            "create_at=#{create_at},update_at=#{update_at},login_at=#{login_at} where id = #{id}")
    Boolean updateUserById(User user);

    /**
     * 根据用户名和密码获取一个用户，该接口主要用于登陆
     * @param userName 用户名
     * @param Password 密码
     * @return   用户登陆信息实体类
     */
    @Deprecated
    @Select("select * from users where username = #{userName} and password = #{password}")
    User findUserByUNameAndPwd(@Param("userName") String userName, @Param("password") String Password);

    /**
     * 根据用户名获取用户
     * @param userName 用户名
     * @return
     */
    @Select("select * from users where username = #{userName}")
    User findUserByName(@Param("userName") String userName);







    /**
     *  根据用户id 获取用户信息。
     * @param userId  用户id
     * @return 用户信息实体对象
     */
    @Select("select * from users_extends where user_id = #{user_id}")
    UserExtend findUserInfoById(@Param("user_id") Integer userId);

    /**
     * 修改密碼
     * @param id
     * @param newPwd
     * @return
     */
    @Update("update users set password = #{newPwd} where id = #{id}")
    Boolean changePassword(@Param("id") Integer id, @Param("newPwd") String newPwd);

}
