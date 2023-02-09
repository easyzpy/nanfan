package com.randing.system.service;

import com.randing.system.domain.po.LandFavorites;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.FavoriteReqDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-02-06
 */
public interface ILandFavoritesService extends IService<LandFavorites> {


    @Transactional
    int addFavoriteLand(FavoriteReqDTO favoriteReqDTO);

    int removeFavoriteLand(FavoriteReqDTO favoriteReqDTO);
}
