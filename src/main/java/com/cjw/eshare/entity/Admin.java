package com.cjw.eshare.entity;

import lombok.*;

import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 22:21
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private Integer id;
    private String admin_user;
    private String admin_pass;
    private String admin_email;
    private Date login_at;
    private String login_ip;
    private Date create_at;
}
