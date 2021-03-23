package com.cjw.eshare.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 23:02
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Favorite implements Serializable {
    private Integer id;
    private Integer user_id;
    private Integer file_id;
    private Date create_at;
}
