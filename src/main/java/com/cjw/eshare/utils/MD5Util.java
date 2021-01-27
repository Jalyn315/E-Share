package com.cjw.eshare.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author cj.w
 * @date 2021/1/27 22:38
 */
public class MD5Util {

    public static String md5Encryption(String password) {

        try{
            MessageDigest md = MessageDigest.getInstance("md5");
            //通过MD5计算摘要，返回一个字节数组
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            //再将字节码编码为用a-z A-Z 0-9 一共64个字符表示得要存储到数据库得字符串，所以又叫BASE64编码算法
            String encryptionPwd = Base64.getEncoder().encodeToString(bytes);
            return encryptionPwd;
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null; //发生异常返回空
    }
}
