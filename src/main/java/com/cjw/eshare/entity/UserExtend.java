package com.cjw.eshare.entity;

import lombok.*;

import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 22:18
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserExtend {
    private Integer id;
    private Integer user_id;
    private String realname;
    private String sex;
    private Date birthday;
    private String description;
}
