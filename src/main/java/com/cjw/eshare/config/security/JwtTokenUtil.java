package com.cjw.eshare.config.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author cj.w
 * @date 2021/2/27 11:22
 */
@Component
public class JwtTokenUtil {

     private static final String CLAIM_KEY_USERNAME = "sub";
     private static final String CLAIM_KEY_CREATED = "create"; //创建时间

     @Value("${jwt.secret}")
     private String secret;
     @Value("${jwt.expiration}")
     private Long expiration;  //token 过期时间

    /**
     * 根据用户信息生成Token
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载生成JWT Token
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)  //添加荷载
                .setExpiration(generateExpirationDate())  //设置失效时间
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        //需要先获取荷载，因为用户名存放在荷载中
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            //如果获取用户名出现异常，username = null
            username = null;
        }
        return username;
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 判断token是否有效
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        /**
         * 判断token是否有效主要做两个判断
         * 1.判断token中username是否等于UserDetails中的usename
         * 2.判断token是否已经过期
         */
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        /**
         * 1.获取token的过期时间
         * 2.判断token的过期时间是否大于在当前时间的前面
         */
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }


    /**
     * 从token里面获取失效时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 判断token是否可以被刷新
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        //如果token已经过期则可以刷新
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        /**
         * 刷新token只需要将token的生成时间改成当前时间，然后再重新生成token即可。
         */
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 生成token失效时间
     * @return
     */
    private Date generateExpirationDate() {
        //失效时间 =  当前系统的时间 + 我们配置的有效时间
        return new Date(System.currentTimeMillis() + expiration);
    }


}
