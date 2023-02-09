package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.domain.po.LandFavorites;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.vo.FavoriteReqDTO;
import com.randing.system.mapper.LandFavoritesMapper;
import com.randing.system.mapper.LandInforMapper;
import com.randing.system.service.ILandFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-02-06
 */
@Service
@Slf4j
public class LandFavoritesServiceImpl extends ServiceImpl<LandFavoritesMapper, LandFavorites> implements ILandFavoritesService {

    @Resource
    private LandFavoritesMapper landFavoritesMapper;
    @Resource
    private LandInforMapper landInforMapper;

    @Override
    @Transactional
    public int addFavoriteLand(FavoriteReqDTO favoriteReqDTO) {
        if (favoriteReqDTO == null || favoriteReqDTO.getLandInfoId() == null) {
            throw new BaseException("土地id不能为空");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Long nanId = jwtUser.getNanUser().getId();
        Integer integer = landFavoritesMapper.selectCount(Wrappers.lambdaQuery(LandFavorites.class).eq(LandFavorites::getUserId, nanId).eq(LandFavorites::getLandId, favoriteReqDTO.getLandInfoId()));
        if (integer > 0) {
            throw new BaseException("已经收藏过了");
        }
        LandInfor landInfor = landInforMapper.selectById(favoriteReqDTO.getLandInfoId());
        if (landInfor == null) {
            log.error("landInfoId:{}", favoriteReqDTO.getLandInfoId());
            throw new BaseException("收藏的土地不存在");
        }

        //加入收藏
        LandFavorites landFavorites = new LandFavorites();
        landFavorites.setLandName(landInfor.getLandName());
        landFavorites.setLandId(favoriteReqDTO.getLandInfoId());
        landFavorites.setUserId(Integer.parseInt(String.valueOf(nanId)));
        landFavoritesMapper.insert(landFavorites);
        return 0;
    }

    @Override
    public int removeFavoriteLand(FavoriteReqDTO favoriteReqDTO) {
        if (favoriteReqDTO == null || favoriteReqDTO.getLandInfoId() == null) {
            throw new BaseException("土地id不能为空");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Long nanId = jwtUser.getNanUser().getId();
        List<LandFavorites> landFavorites = landFavoritesMapper.selectList(Wrappers.lambdaQuery(LandFavorites.class)
                .eq(LandFavorites::getUserId, nanId)
                .eq(LandFavorites::getLandId, favoriteReqDTO.getLandInfoId()));
        if (CollectionUtils.isEmpty(landFavorites)) {
            throw new BaseException("此块土地还未被收藏");
        }
        int i = landFavoritesMapper.deleteBatchIds(landFavorites.stream().map(LandFavorites::getId).collect(Collectors.toList()));
        return i;
    }
}
