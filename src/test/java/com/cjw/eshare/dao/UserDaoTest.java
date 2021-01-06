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
        userDao.createUser(user);
    }


    @Test
    public void deleteUserById() {
        if (userDao.findUserById(2) != null) {
            logger.info("delete user:" + userDao.findUserById(2));
            userDao.deleteUserById(2);
        } else {
            logger.info("have no user of id = 1");
        }
    }


    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(3);
        user.setUsername("jalyn" + UUID.randomUUID());
        user.setPassword("root");
        user.setAvatar_url("D:/avatar");
        user.setEmail("jiansir315@gmail.com");
        user.setCreate_at(new Date());
        user.setLogin_at(new Date());
        user.setPhone("12345678910");
        user.setUpdate_at(new Date());
        logger.info("create user account\nInfo:" + user);
        userDao.updateUserById(user);
    }
}
