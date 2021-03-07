package com.cjw.eshare.constant;

import org.springframework.context.annotation.PropertySource;

/**
 * @author cj.w
 * @date 2021/2/21 9:22
 */
@PropertySource("classpath:config.properties")
public class FileConfigConstant {

    public static String UPLOAD_PATH = "D:/eshare/files";
}
