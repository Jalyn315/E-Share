package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/4 23:28
 */
@Mapper
public interface AdminDao {

    /**
     * 管理员查询所有用户
     * @return
     */
    @Select("SELECT * FROM users_extends;")
    List<User> findAllUser();


    /**
     * 创建一个管理员账户
     * @param admin 管理员实体类对象
     */
    @Insert("insert into admins(admin_user, admin_pass, admin_email, login_at, login_ip, create_at" +
            ") value (#{admin_user}, #{admin_pass}, #{admin_email}, #{login_at}, #{login_ip}, #{create_at})")
    void insertAdmin(Admin admin);

    /**
     * 根据管理员名查询管理员
     * @param username
     * @return
     */
    @Select("select * from admins where admin_user = #{username}")
    Admin getAdminByName(@Param("username") String username);

    /**
     * 根据用户 id 查询用户
     * @param user_id
     * @return
     */
    @Select("SELECT * FROM users_extends WHERE id=#{user_id};")
    User getUser(Integer user_id);

    /**
     * 管理员更新用户密码
     * @param userId
     * @param password
     * @return
     */
    @Update("UPDATE users SET PASSWORD = #{password} WHERE id = #{userId};")
    Integer updateUserPasswordById(Integer userId, String password);

    /**
     * 更新用户信息
     * @param userModel
     * @return
     */
    @Update("UPDATE users AS u INNER JOIN users_extends AS ue"
            + "ON u.id = ue.user_id"
            + "SET u.phone=#{phone},"
            + "u.email=#{email},"
            + "u.password=#{newPassword},"
            + "ue.birthday=#{birthday},"
            + "ue.sex=#{sex},"
            + "ue.description=#{description};")
    Integer updateUserInfo(UserModel userModel);
}
