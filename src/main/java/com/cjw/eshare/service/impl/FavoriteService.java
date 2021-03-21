package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.FavoriteDao;
import com.cjw.eshare.entity.Favorite;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.FavoriteModel;
import com.cjw.eshare.service.IFavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:12
 */
@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;

    /**
     * 获取当前登陆的用户的Id
     * @return
     */
    private Integer getUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public CRModel createFavorite(FavoriteModel favoriteModel) {
        Favorite favorite = new Favorite(null, favoriteModel.getUserId(),favoriteModel.getFileId(),favoriteModel.getCreatTime());
        return favoriteDao.createFavorite(favorite) == 1
                ?CRModel.success(SuccessDescription.UPDATE_FAVORITE_SUCCESS)
                :CRModel.error(ErrorDescription.UPDATE_FAVORITE_ERR);
    }

    @Override
    public CRModel deleteFavorite(Integer file_id) {
        return favoriteDao.deleteFavorite(file_id) == 1
                ?CRModel.success(SuccessDescription.DELETE_FAVORITE_SUCCESS)
                :CRModel.error(ErrorDescription.DELETE_FAVORITE_ERR);
    }

    @Override
    public CRModel findAllByUserId() {
        Integer id = getUserId();
        List<Favorite> userFavorite = null;
        if ((userFavorite = favoriteDao.findAllByUserId(id)) == null) {
            return CRModel.error(ErrorDescription.FIND_NO_FAVORITE);
        }
        return CRModel.success("",userFavorite);
    }

    @Override
    public Integer findCountByFileId(Integer file_id) {
        return favoriteDao.findCountByFileId(file_id);
    }
}
