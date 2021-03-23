package com.cjw.eshare.service;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.FavoriteModel;

/**
 * @author cj.w
 * @date 2021/1/6 23:08
 */
public interface IFavoriteService {
    /**
     * 创建一个用户收藏
     * @param file_id
     */
    CRModel createFavorite(Integer file_id);

    /**
     * 根据用户 id 移除一个收藏
     * @param file_id
     */
    CRModel deleteFavorite(Integer file_id);

    /**
     * 根据用户 id 获取用户的收藏信息
     * @return
     */
    CRModel findAllByUserId();

    /**
     * 根据 文件id 查看文件收藏次数
     * @param file_id
     * @return
     */
    Integer findCountByFileId(Integer file_id);


}
