package com.cjw.eshare.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cj.w
 * @date 2021/1/27 23:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    public void loginTest() {
        final String userName = "root";
        final String password = "root";

        userService.login(userName, password);

    }


}
