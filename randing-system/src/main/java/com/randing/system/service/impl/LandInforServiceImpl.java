package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandFavorites;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.po.LandInforPicture;
import com.randing.system.domain.po.LandInforService;
import com.randing.system.domain.po.LandSevice;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.mapper.LandFavoritesMapper;
import com.randing.system.mapper.LandInforMapper;
import com.randing.system.mapper.LandInforPictureMapper;
import com.randing.system.mapper.LandInforServiceMapper;
import com.randing.system.mapper.LandSeviceMapper;
import com.randing.system.service.ILandInforService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LandInforPictureMapper landInforPictureMapper;
    @Override
    public Page<LandInfor> listPage(LandInforVo landInforVo) {
        QueryWrapper<LandInfor> wrapper = Wrappers.query();
        wrapper.like(StringUtils.isNotBlank(landInforVo.getLandName()), "land_name", landInforVo.getLandName())
                .eq(landInforVo.getLandMold() != null, "land_mold", landInforVo.getLandMold())
                .eq(landInforVo.getLandType() != null, "land_type", landInforVo.getLandType())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilAcidBase()), "Land_soil_acid_base", landInforVo.getLandSoilAcidBase())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilType()), "Land_soil_type", landInforVo.getLandSoilType())
                .like(StringUtils.isNotBlank(landInforVo.getLandCropType()), "land_crop_type", landInforVo.getLandCropType())
                .eq(StringUtils.isNotBlank(landInforVo.getLandSoilNature()), "land_soil_nature", landInforVo.getLandSoilNature())
                //三亚陵水
                .eq(StringUtils.isNotBlank(landInforVo.getLandAscription()), "land_ascription", landInforVo.getLandAscription())
                //土地可用面积
                .ge(landInforVo.getLandAreaSurplusStart() != null, "land_area_surplus", landInforVo.getLandAreaSurplusStart())
                .le(landInforVo.getLandAreaSurplusEnd() != null, "land_area_surplus", landInforVo.getLandAreaSurplusEnd())
                //土地总面积
                .ge(landInforVo.getLandAreaTotalStart() != null, "land_area_total", landInforVo.getLandAreaTotalStart())
                .le(landInforVo.getLandAreaTotalEnd() != null, "land_area_total", landInforVo.getLandAreaTotalEnd())
                //土地已用面积
                .ge(landInforVo.getLandAreaUsableStart() != null, "land_area_usable", landInforVo.getLandAreaUsableStart())
                .le(landInforVo.getLandAreaUsableEnd() != null, "land_area_usable", landInforVo.getLandAreaUsableEnd())
                .ge(landInforVo.getLandPrice() != null, "land_price", landInforVo.getLandPrice())
                .le(landInforVo.getLandMaxPrice() != null, "land_max_price", landInforVo.getLandMaxPrice())
                .in(!CollectionUtils.isEmpty(landInforVo.getIds()), "id", landInforVo.getIds())
                .isNotNull("land_sequence")
                .orderBy(landInforVo.getLandAreaTotalOrder() != null, landInforVo.getLandAreaTotalOrder() == OrderByEnum.asc, "land_area_total")
                .orderBy(landInforVo.getLandPriceOrder() != null, landInforVo.getLandPriceOrder() == OrderByEnum.asc, "land_price")
                .orderBy(landInforVo.getLandMaxPriceOrder() != null, landInforVo.getLandMaxPriceOrder() == OrderByEnum.asc, "land_max_price")
                .orderBy(landInforVo.getLandReleaseTimeOrder() != null, landInforVo.getLandReleaseTimeOrder() == OrderByEnum.asc, "land_release_time")

                .orderByDesc("land_mold =1 and land_type = 0")
                .orderByAsc("land_sequence");
        Page<LandInfor> landInforPage = baseMapper.selectPage(new Page<>(landInforVo.getPage(), landInforVo.getPageSize()),wrapper);
        return landInforPage;

    }
    @Override
    public Page<LandInfor> favorite(LandInforVo landInforVo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        Long nanUserId = jwtUser.getNanUser().getId();
        List<LandFavorites> landFavorites = landFavoritesMapper.selectList(Wrappers.lambdaQuery(LandFavorites.class).eq(LandFavorites::getUserId, nanUserId));
        if (CollectionUtils.isEmpty(landFavorites)) {
            return new Page<>(1,10,0);
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
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
//        Long nanUserId = jwtUser.getNanUser().getId();
        Integer integer = landFavoritesMapper.selectCount(Wrappers.lambdaQuery(LandFavorites.class).eq(LandFavorites::getLandId, id)
                .eq(LandFavorites::getUserId, ((JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNanUser().getId()));
        landInforVo.setFavoriteStatus(integer ==0?0:1);
        //查询相关服务
        List<LandInforService> landInforServices = landInforServiceMapper.selectList(Wrappers.lambdaQuery(LandInforService.class).eq(LandInforService::getInforId, id));
        if (!landInforServices.isEmpty()) {
            List<LandSevice> landSevices = landSeviceMapper.selectList(Wrappers.lambdaQuery(LandSevice.class).in(LandSevice::getId, landInforServices.stream().map(LandInforService::getServiceId).collect(Collectors.toList())));
            landInforVo.setLandServices(landSevices);
        }
        //查询图片
        List<LandInforPicture> landInforPictures = landInforPictureMapper.selectList(Wrappers.lambdaQuery(LandInforPicture.class).eq(LandInforPicture::getInforId, id));
        landInforVo.setPictureEntities(landInforPictures);
        return landInforVo;
    }

    @Override
    public List<String> landAscriptionList() {
        List<LandInfor> landInfors = baseMapper.selectList(new QueryWrapper<LandInfor>().select("DISTINCT land_ascription").isNotNull("land_ascription"));
        if (!CollectionUtils.isEmpty(landInfors)) {
            List<String> collect = landInfors.stream().map(LandInfor::getLandAscription).collect(Collectors.toList());
            return collect;
        }
        return null;
    }
}
