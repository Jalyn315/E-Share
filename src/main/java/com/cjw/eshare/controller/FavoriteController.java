package com.cjw.eshare.controller;

import com.cjw.eshare.model.CRModel;
import com.cjw.eshare.model.FavoriteModel;
import com.cjw.eshare.service.impl.FavoriteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author cj.w
 * @date 2021/1/6 23:24
 */
@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @ApiOperation(value = "添加收藏")
    @PostMapping("/add_user_favorite")
    public CRModel addUserFavorite(@RequestBody FavoriteModel favoriteModel) { return favoriteService.createFavorite(favoriteModel); }

    @ApiOperation(value = "删除一个收藏")
    @PostMapping("/delete_user_favorite{file_id}")
    public CRModel deleteUserFavorite(@PathVariable("file_id") Integer id) { return favoriteService.deleteFavorite(id); }

    @ApiOperation(value = "获取用户的收藏信息")
    @GetMapping("/user_favorite_info")
    public CRModel getUserFavoriteInfo() {return favoriteService.findAllByUserId();}

    @ApiOperation(value = "返回一个文件的收藏次数")
    @PostMapping("/count_favorite_file{file_id}")
    public Integer countFavoriteFile(@PathVariable("file_id") Integer id) { return favoriteService.findCountByFileId(id); }
}
