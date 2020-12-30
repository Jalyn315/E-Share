package com.cjw.eshare.entity;

import lombok.*;

/**
 * @author cj.w
 * @date 2020/12/30 22:47
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class File {
    private Integer id;
    private Integer user_id;
    private String filename;
    private Integer type_id;
    private Double file_size;
    private String file_url;
    private String description;
    private Integer is_show;
    private Integer is_download;
    private Long praise_amount;
    private Long download_amount;
}
