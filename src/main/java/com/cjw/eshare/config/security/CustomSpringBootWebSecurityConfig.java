package com.cjw.eshare.config.security;

import com.cjw.eshare.entity.Admin;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.service.IAdminService;
import com.cjw.eshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author cj.w
 * @program: eshare
 * @description: 多个策略配置
 * @create: 2021/3/8 15:02
 */

//TODO 这个类用有对不同的接口实现不同的安全策略，目前还没有完善，整个系统已经一处UserDetail的Bean
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
//@EnableWebSecurity
//@ConditionalOnClass(WebSecurityConfigurerAdapter.class)
//@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class CustomSpringBootWebSecurityConfig {

    @Autowired
    private static RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private static RestAuthorizationEntryPoint restAuthorizationEntryPoint;

    /**
     * 使用静态内部类配置后台接口安全策略
//     */
//    @Configuration

    static class AdminConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private IAdminService adminService;


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(adminDetailsService()).passwordEncoder(passwordEncoder());
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
                    .antMatchers("/admin/*")
                    .authenticated()
                    .and()
                    //禁用缓存
                    .headers()
                    .cacheControl();

            //添加jwt 登陆授权过滤器
            http.addFilterBefore(jwtAdminAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            //添加自定义未授权和未登录结果返回
            http.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthorizationEntryPoint);
        }
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

        public UserDetailsService adminDetailsService() {
            return username -> {
                Admin admin = adminService.getAdminByUserName(username);
                if (null != admin) {
                    return admin;
                }
                return null;
            };
        }

        public JwtAdminAuthenticationTokenFilter jwtAdminAuthenticationTokenFilter() {
            return new JwtAdminAuthenticationTokenFilter();
        }

        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

    }


//    @Configuration
    @Order(1)
    static class UserConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
        private IUserService userService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        }

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


        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //关闭Session
                    .and()
                    .authorizeRequests()
                    .antMatchers("/*")
                    .authenticated()
                    .and()
                    //禁用缓存
                    .headers()
                    .cacheControl();
            //添加jwt 登陆授权过滤器
            http.addFilterBefore(jwtuserAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
            http.exceptionHandling()
                    .accessDeniedHandler(restfulAccessDeniedHandler)
                    .authenticationEntryPoint(restAuthorizationEntryPoint);
        }


        public UserDetailsService userDetailsService() {
            return username -> {
                User user = userService.getUserByUserName(username);
                if (null != user) {
                    return user;
                }
                return null;
            };
        }

        public JwtUserAuthenticationTokenFilter jwtuserAuthenticationTokenFilter() {
            return new JwtUserAuthenticationTokenFilter();
        }

        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
