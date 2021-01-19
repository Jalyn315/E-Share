package com.cjw.eshare;

import com.cjw.eshare.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EshareApplicationTests {

	@Autowired
	UserDao userDao;
	@Test
	void contextLoads() {
		userDao.deleteUserById(5);
	}

}
