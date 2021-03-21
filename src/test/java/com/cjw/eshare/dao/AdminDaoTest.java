package com.cjw.eshare.dao;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cj.w
 * @program: eshare
 * @description: 管理员dao层测试类
 * @create: 2021/3/1 22:34
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminDaoTest {

    @Autowired
    private AdminDao adminDao;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void createAdminTest() throws Exception{
        Admin admin = new Admin();
        admin.setAdmin_user("admin");
        admin.setAdmin_pass(MD5Util.md5Encryption("123"));
        admin.setAdmin_email("admin@com");
        admin.setLogin_at(new Date());
        admin.setLogin_ip("192.168.72.1");
        logger.info("create user account\nInfo:" + admin);
        final User result = null;
        adminDao.insertAdmin(admin);

        if(result != null) {
            logger.info("💋💋💋💋💋💋💋💋Account creation successful:" + result);
        } else {
            logger.info("😭😭😭😭😭😭😭😭Account creation failed! Please contact the administrator!");
        }
    }

}
