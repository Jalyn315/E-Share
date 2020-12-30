package com.cjw.eshare.entity;

import lombok.*;

import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 20:46
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String avatar_url;
    private String phone;
    private String email;
    private String password;
    private Date create_at;
    private Date update_at;
    private Date login_at;
}
