package com.cjw.eshare;

import com.cjw.eshare.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import springfox.documentation.spring.web.plugins.Docket;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class EshareApplicationTests {

	@Autowired
	UserDao userDao;


	@Autowired
	private Docket docket;

	@Test
	void contextLoads() {

		System.out.println(docket instanceof Object);

		userDao.deleteUserById(5);
	}

}
