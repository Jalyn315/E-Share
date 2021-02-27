package com.cjw.eshare.entity;

import lombok.*;

import java.io.Serializable;
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
public class Type implements Serializable {
    private Integer id;
    private String name;
    private Date create_at;
    private Date update_at;
}
