package com.cjw.eshare.service.impl;

import com.cjw.eshare.constant.ErrorDescription;
import com.cjw.eshare.constant.SuccessDescription;
import com.cjw.eshare.dao.FavoriteDao;
import com.cjw.eshare.entity.Favorite;
import com.cjw.eshare.entity.User;
import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.FavoriteModel;
import com.cjw.eshare.service.IFavoriteService;
import com.cjw.eshare.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author cj.w
 * @date 2021/1/6 23:12
 */
@Service
public class FavoriteService implements IFavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;
    @Autowired
    private IUserService userService;

    @Override
    public CRModel createFavorite(Integer file_id) {
        Favorite favorite = new Favorite();
        favorite.setUser_id(userService.getCurrentUserId());
        favorite.setFile_id(file_id);
        favorite.setCreate_at(new Date());
        //TODO 先判断当前文件是否存在，然后判断该文件是否已经被当当前用户收藏了，如果以被收藏侧调用删除接口。
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
        Integer id = userService.getCurrentUserId();
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
