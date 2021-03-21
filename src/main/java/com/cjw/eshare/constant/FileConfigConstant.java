package com.cjw.eshare.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * @author cj.w
 * @date 2021/2/21 9:22
 */

@Configuration
@PropertySource("classpath:config.properties")
public class FileConfigConstant {

    //本地路径
    public static String UPLOAD_PATH;
    //文件大小
    public static long MAX_SIZE;

    public static String VIA_PATH;

    @Value("${file.maxSize}")
    public void setMaxSize(long maxSize) {
        FileConfigConstant.MAX_SIZE = maxSize;
    }

    @Value("${file.uploadPath}")
    public void setUploadPath(String uploadPath) {
        FileConfigConstant.UPLOAD_PATH = uploadPath;
    }

    @Value("${viaPath}")
    public void setViaPath(String viaPath) {
        FileConfigConstant.VIA_PATH = viaPath;
    }
}
