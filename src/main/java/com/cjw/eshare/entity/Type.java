package com.cjw.eshare.entity;

import lombok.*;

import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 22:53
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Type {
    private Integer id;
    private String name;
    private Date create_at;
    private Date update_at;
}
