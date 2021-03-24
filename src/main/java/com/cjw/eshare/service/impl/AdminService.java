package com.cjw.eshare.service.impl;

import com.cjw.eshare.config.security.JwtTokenUtil;
import com.cjw.eshare.constant.ErrorCode;
import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.AdminDao;
import com.cjw.eshare.dao.UserDao;
import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.UserModel;
import com.cjw.eshare.service.*;
import com.cjw.eshare.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cj.w
 * @date 2021/1/6 23:11
 */
@Service
public class AdminService implements IAdminService {

//    @Autowired
//    private UserDetailsService userDetailsService;
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IDownloadService downloadService;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHead;


    /**
     * 登陆之后返回token
     * @param username
     * @param password
     * @param captchaCode
     * @param request
     * @return
     */
    @Override
    public CRModel login(String username, String password, String captchaCode, HttpServletRequest request) {

        String captcha = (String) request.getSession().getAttribute("captcha");
        if ("".equals(captcha) || !captcha.equalsIgnoreCase(captchaCode)) {
            return CRModel.error("验证码输入错误，请重新输入!");
        }
        //获取UserDetails
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        //判断是否存在该账户信息
//        if (null == userDetails) {
//            return CRModel.error("您输入的帐户名不存在!");
//        }
        //判断密码是否正确
        Admin userDetails = adminDao.getAdminByName(username);
        if (!MD5Util.md5Encryption(password).equals(userDetails.getPassword())) {
            System.out.println(MD5Util.md5Encryption(password) + "*****" + userDetails.getPassword());
            return CRModel.error("用户名或密码错误，请联系管理员");
        }

        //更新security登陆用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //获取token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CRModel.success(SuccessDescription.LOGIN_SUCCESS,tokenMap);
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminDao.getAdminByName(username);
    }

    @Override
    public CRModel getAllUser() {
        List<User> users = null;
       return (users = adminDao.findAllUser()) == null
                ?CRModel.error(ErrorDescription.FIND_NO_USER)
                :CRModel.success("",users);
    }

    @Override
    public CRModel getUser(Integer user_id) {
        User user = null;
       return (user = adminDao.getUser(user_id)) == null
            ?CRModel.error(ErrorDescription.FIND_NO_USER)
            :CRModel.success("",user);
    }

    @Override
    public CRModel updateUserPasswordById(UserModel userModel) {
        //先判断用户输入的旧密码是否正确
        if (!MD5Util.md5Encryption(userModel.getOldPassword()).equals(userDao.findPasswordById(userModel.getUserId()))) {
            return CRModel.error(ErrorCode.PWD_ERR, ErrorDescription.CHANGE_PWD_ERR);
        }
        return adminDao.updateUserPasswordById(userModel.getId(),userModel.getNewPassword()) == 1
                ? CRModel.success(SuccessDescription.UPDATE_USER_PASSWORD_SUCCESS)
                : CRModel.error(ErrorDescription.UPDATE_USER_PASSWORD_ERR);
    }

    @Override
    public CRModel updateUserInfo(UserModel userModel) {
        return adminDao.updateUserInfo(userModel) == 1
                ? CRModel.success(SuccessDescription.UPDATE_USER_INFO_SUCCESS)
                : CRModel.error(ErrorDescription.UPDATE_USER_INFO_ERR);
    }

    @Override
    public CRModel getAllTypeInfo() {
        return CRModel.success("",typeService.getAllType());
    }

    @Override
    public CRModel deleteFileInfoById(Integer id) {
        return CRModel.success("",downloadService.deleteDownloadById(id));
    }

    @Override
    public CRModel getAllDownloadInfo() {
        return downloadService.getAllDownloadInfo();
    }


    @Override
    public CRModel deleteUploadInfoById(Integer id) {
        return uploadService.deleteById(id);
    }


}
