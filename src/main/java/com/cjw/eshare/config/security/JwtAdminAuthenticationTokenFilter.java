package com.cjw.eshare.config.security;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.service.IAdminService;
import com.cjw.eshare.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.AnnotatedArrayType;

/**
 * @author cj.w
 * @program: eshare
 * @description: JWT 登陆授权过滤器
 * @create: 2021/2/27 22:18
 */

public class JwtAdminAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IAdminService adminService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //获取请求头中的tokenHeader
        String authHeader = request.getHeader(tokenHeader);

        //存在token
        if (null != authHeader && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());  //获取到请求头中的token
            String username = jwtTokenUtil.getUserNameFromToken(authToken); //通过token获取到用户名
            //token 存在用户名但是未登陆
            if (null != username && null == SecurityContextHolder.getContext().getAuthentication()) {
                //登陆
                UserDetails userDetails = adminDetailsService().loadUserByUsername(username); // 先获取userDetails
                //TODO 需要确实使用TokenHead还是上面的authToken
                //验证token是否有效
                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response); //放行请求是、
    }

    public UserDetailsService adminDetailsService() {
        return username -> {
            Admin admin = adminService.getAdminByUserName(username);
            if (null != admin) {
                return admin;
            }
            return null;
        };
    }
}
