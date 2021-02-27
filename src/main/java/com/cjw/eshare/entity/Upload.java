package com.cjw.eshare.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cj.w
 * @date 2020/12/30 22:55
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Upload implements Serializable {
     private Integer id;
     private Integer user_id;
     private Integer file_id;
     private Date upload_at;
}
