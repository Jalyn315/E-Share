package com.cjw.eshare.dao;

import com.cjw.eshare.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author cj.w
 * @date 2020/12/31 11:08
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserDao userDao;

    /**
     * 创建一个账户
     */
    @Test
    public void createUserTest() {
        User user = new User();
        user.setUsername("weilu" + UUID.randomUUID());
        user.setPassword("root");
        user.setAvatar_url("D:/avatar");
        user.setEmail("jiansir315@gmail.com");
        user.setCreate_at(new Date());
        user.setLogin_at(new Date());
        user.setPhone("12345678910");
        user.setUpdate_at(new Date());
        logger.info("create user account\nInfo:" + user);
        final User result = null;
        userDao.insertUser(user);

        if(result != null) {
            logger.info("💋💋💋💋💋💋💋💋Account creation successful:" + result);
        } else {
            logger.info("😭😭😭😭😭😭😭😭Account creation failed! Please contact the administrator!");
        }
    }


    /**
     * 删除一个账户
     */
    @Test
    public void deleteUserById() {
        if (userDao.findUserById(2) != null) {
            logger.info("delete user:" + userDao.findUserById(2));
            userDao.deleteUserById(2);
        } else {
            logger.info("have no user of id = 1");
        }
    }


    /**
     * 更新用户登陆的信息
     */
    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(6);
        user.setUsername("jalyn" + UUID.randomUUID());
        user.setPassword("root");
        user.setAvatar_url("D:/avatar");
        user.setEmail("jiansir315@gmail.com");
        user.setCreate_at(new Date());
        user.setLogin_at(new Date());
        user.setPhone("12345678910");
        user.setUpdate_at(new Date());
        logger.info("create user account\nInfo:" + user);
        System.out.println(
                userDao.updateUserById(user)
        );
    }

    /**
     * 登陆测试
     */
    @Test
    public void loginTest(){
        final String userName = "root44c4d605-0289-4694-9fd6-64e8a93ea332";
        final String password = "root";
        final  User user = userDao.findUserByUNameAndPwd(userName, password);
        if (user != null) {
            logger.info("登陆成功:\n" + "username:" + user.getUsername());
        } else {
            logger.info("登陆失败！用户名或者密码错误！");
        }
    }

    /**
     * 修改密码
     */
    @Test
    public void changePasswordTest() {
        if (userDao.changePassword(6, "123456")) {
            logger.info("💋💋💋💋💋💋💋💋 update password success!");
        } else {
            logger.info("😭😭😭😭😭😭😭😭 update failed!");
        }
    }



}
