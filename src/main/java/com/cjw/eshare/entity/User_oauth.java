package com.cjw.eshare.entity;

import lombok.*;

/**
 * @author cj.w
 * @date 2020/12/30 22:23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User_oauth {
    private Integer id;
    private Integer user_id;
    private String oauth_type;
    private String oauth_id;
    private String union_id;
    private String credential;
}
