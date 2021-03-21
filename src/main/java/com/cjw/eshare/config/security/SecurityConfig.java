package com.cjw.eshare.config.security;

import com.cjw.eshare.entity.User;
import com.cjw.eshare.service.IAdminService;
import com.cjw.eshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author cj.w
 * @program: eshare
 * @description: Security配置类
 * @create: 2021/2/27 21:46
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUserService userService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    /**
     *该方法用于配置security在启动时使用我们重写的userDetails
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    /**
     * 放行的请求
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/login",
                "/admin/login",
                "/logout",
                "/css/**",
                "/js/**",
                "/index.html",
                "favicon.icon",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**",
                "/captcha",
                "/register"
        );
    }

    /**
     * 配置security
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //使用JWT.不需要csrf
        http.csrf()
                .disable()
                //基于token，不需要session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
            //所有请求都需要认证
                .anyRequest()
                .authenticated()
                .and()
                //禁用缓存
            .headers()
                .cacheControl();

        //添加jwt 登陆授权过滤器
        http.addFilterBefore(jwtUserAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);

    }

    //TODO 确认此处是否应该这样添加两个身份的detail
    /**
     * 管理员的Details
     * @return
     */

//    @Bean
//    public UserDetailsService AdminDetailsService() {
//        return username -> {
//            Admin admin = adminService.getAdminByUserName(username);
//            if (null != admin) {
//                return admin;
//            }
//            return null;
//        };
//    }

    /**
     * 普通用户的Details
     * @return
     */
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username-> {
            User user = userService.getUserByUserName(username);
            if (null != user) {
                return user;
            }
            return null;
        };
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUserAuthenticationTokenFilter jwtUserAuthenticationTokenFilter() {
        return new JwtUserAuthenticationTokenFilter();
    }




}
