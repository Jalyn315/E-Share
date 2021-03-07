package com.cjw.eshare.config.security;

import com.cjw.eshare.constant.ErrorCode;
import com.cjw.eshare.model.CRModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author cj.w
 * @program: eshare
 * @description: 当未登陆或者token失效访问接口时，自定义返回结果
 * @create: 2021/2/27 23:24
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        CRModel model = CRModel.error("尚未登陆，请登陆");
        model.setCode(ErrorCode.NO_PERMISSION_ERR);
        out.write(new ObjectMapper().writeValueAsString(model));
        out.flush();
        out.close();
    }
}
