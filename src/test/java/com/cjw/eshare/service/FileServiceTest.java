package com.cjw.eshare.service;

import com.cjw.eshare.constant.FileConfigConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author cj.w
 * @program: eshare
 * @description: 文件服务层测试
 * @create: 2021/3/10 21:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {


    @Test
    public void getFileUploadPathTest() {
        System.out.println(FileConfigConstant.UPLOAD_PATH);
    }
}
