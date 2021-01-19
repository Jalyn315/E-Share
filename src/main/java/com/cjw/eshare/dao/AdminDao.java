package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author cj.w
 * @date 2021/1/4 23:28
 */
@Mapper
public interface AdminDao {

    /**
     * 创建一个管理员账户
     * @param admin 管理员实体类对象
     */
    @Insert("insert into admins(admin_user, admin_pass, admin_email, login_at, login_ip, create_at" +
            ") value (#{admin_user}, #{admin_pass}, #{admin_email}, #{login_at}, #{login_ip}, #{create_at})")
    void insertAdmin(Admin admin);






}
