package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandFavorites;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.po.LandInforService;
import com.randing.system.domain.po.LandSevice;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.mapper.LandFavoritesMapper;
import com.randing.system.mapper.LandInforMapper;
import com.randing.system.mapper.LandInforServiceMapper;
import com.randing.system.mapper.LandSeviceMapper;
import com.randing.system.service.ILandFavoritesService;
import com.randing.system.service.ILandInforService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2022-12-15
 */
@Service
public class LandInforServiceImpl extends ServiceImpl<LandInforMapper, LandInfor> implements ILandInforService {
//    @Value("${baseimageurl}")
//    private String baseImageUrl;
    @Autowired
    private LandFavoritesMapper landFavoritesMapper;
    @Autowired
    private LandInforServiceMapper landInforServiceMapper;

    @Autowired
    private LandSeviceMapper landSeviceMapper;
    @Override
    public Page<LandInfor> listPage(LandInforVo landInforVo) {
        Page<LandInfor> landInforPage = baseMapper.selectPage(new Page<>(landInforVo.getPage(), landInforVo.getPageSize()), Wrappers.lambdaQuery(LandInfor.class)
                .like(StringUtils.isNotBlank(landInforVo.getLandName()), LandInfor::getLandName, landInforVo.getLandName())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilAcidBase()), LandInfor::getLandSoilAcidBase, landInforVo.getLandSoilAcidBase())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilType()), LandInfor::getLandSoilType, landInforVo.getLandSoilType())
                .eq(StringUtils.isNotBlank(landInforVo.getLandCropType()), LandInfor::getLandCropType, landInforVo.getLandCropType())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilNature()), LandInfor::getLandSoilNature, landInforVo.getLandSoilNature())
                //三亚陵水
                .eq(StringUtils.isNotBlank(landInforVo.getLandAscription()), LandInfor::getLandAscription, landInforVo.getLandAscription())
                //土地可用面积
                .ge(landInforVo.getLandAreaSurplusStart() != null, LandInfor::getLandAreaSurplus, landInforVo.getLandAreaSurplusStart())
                .le(landInforVo.getLandAreaSurplusEnd() != null, LandInfor::getLandAreaSurplus, landInforVo.getLandAreaSurplusEnd())
                //土地总面积
                .ge(landInforVo.getLandAreaTotalStart() != null, LandInfor::getLandAreaTotal, landInforVo.getLandAreaTotalStart())
                .le(landInforVo.getLandAreaTotalEnd() != null, LandInfor::getLandAreaTotal, landInforVo.getLandAreaTotalEnd())
                //土地已用面积
                .ge(landInforVo.getLandAreaUsableStart() != null, LandInfor::getLandAreaUsable, landInforVo.getLandAreaUsableStart())
                .le(landInforVo.getLandAreaUsableEnd() != null, LandInfor::getLandAreaUsable, landInforVo.getLandAreaUsableEnd())
                .ge(landInforVo.getLandPrice() != null, LandInfor::getLandPrice, landInforVo.getLandPrice())
                .le(landInforVo.getLandMaxPrice() != null, LandInfor::getLandMaxPrice, landInforVo.getLandMaxPrice())
                .in(!CollectionUtils.isEmpty(landInforVo.getIds()), LandInfor::getId, landInforVo.getIds())
                .orderBy(landInforVo.getLandAreaTotalOrder() != null, landInforVo.getLandAreaTotalOrder() == OrderByEnum.asc, LandInfor::getLandAreaTotal)
                .orderBy(landInforVo.getLandPriceOrder() != null, landInforVo.getLandPriceOrder() == OrderByEnum.asc, LandInfor::getLandPrice)
                .orderBy(landInforVo.getLandMaxPriceOrder() != null, landInforVo.getLandMaxPriceOrder() == OrderByEnum.asc, LandInfor::getLandMaxPrice)
                .orderBy(landInforVo.getLandReleaseTimeOrder() != null, landInforVo.getLandReleaseTimeOrder() == OrderByEnum.asc, LandInfor::getLandReleaseTime)
                .orderBy(landInforVo.getLandAreaTotalOrder() == null
                        && landInforVo.getLandPriceOrder() == null
                        && landInforVo.getLandMaxPriceOrder()== null
                        && landInforVo.getLandReleaseTimeOrder()== null, false, LandInfor::getLandReleaseTime
                )
        );
        return landInforPage;

    }
    @Override
    public Page<LandInfor> favorite(LandInforVo landInforVo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Long nanUserId = jwtUser.getNanUser().getId();
        List<LandFavorites> landFavorites = landFavoritesMapper.selectList(Wrappers.lambdaQuery(LandFavorites.class).eq(LandFavorites::getUserId, nanUserId));
        if (CollectionUtils.isEmpty(landFavorites)) {
            return null;
        }
        landInforVo.setIds(landFavorites.stream().map(LandFavorites::getLandId).collect(Collectors.toList()));
        return this.listPage(landInforVo);

    }

    @Override
    public LandInforVo findById(Long id) {
        LandInfor landInfor = baseMapper.selectById(id);
        LandInforVo landInforVo = new LandInforVo();
        BeanUtils.copyProperties(landInfor, landInforVo);
        //查询收藏状态
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Long nanUserId = jwtUser.getNanUser().getId();
        Integer integer = landFavoritesMapper.selectCount(Wrappers.lambdaQuery(LandFavorites.class).eq(LandFavorites::getLandId, id)
                .eq(LandFavorites::getUserId, ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNanUser().getId()));
        landInforVo.setFavoriteStatus(integer ==0?0:1);
        //查询相关服务
        List<LandInforService> landInforServices = landInforServiceMapper.selectList(Wrappers.lambdaQuery(LandInforService.class).eq(LandInforService::getInforId, id));
        if (!landInforServices.isEmpty()) {
            List<LandSevice> landSevices = landSeviceMapper.selectList(Wrappers.lambdaQuery(LandSevice.class).in(LandSevice::getId, landInforServices.stream().map(LandInforService::getServiceId).collect(Collectors.toList())));
            landInforVo.setLandServices(landSevices);
        }
        return landInforVo;
    }

    @Override
    public List<String> landAscriptionList() {
        List<LandInfor> landInfors = baseMapper.selectList(new QueryWrapper<LandInfor>().select("DISTINCT land_ascription"));
        if (!CollectionUtils.isEmpty(landInfors)) {
            List<String> collect = landInfors.stream().map(LandInfor::getLandAscription).collect(Collectors.toList());
            return collect;
        }
        return null;
    }
}
