package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.jwt.JwtUser;
import com.randing.system.domain.po.LandRetreat;
import com.randing.system.domain.po.LandRetreatFile;
import com.randing.system.mapper.LandRetreatFileMapper;
import com.randing.system.mapper.LandRetreatMapper;
import com.randing.system.service.ILandRetreatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
@Service
public class LandRetreatServiceImpl extends ServiceImpl<LandRetreatMapper, LandRetreat> implements ILandRetreatService {

    @Resource
    private LandRetreatFileMapper landRetreatFileMapper;
    @Resource
    private LandRetreatMapper landRetreatMapper;

    @Override
    public Page<LandRetreat> listPage(LandRetreat landRetreat) {
        Long loginUserId = LoginUser.getLoginUserId();
        QueryWrapper<Class<LandRetreat>> eq = Wrappers.query(LandRetreat.class)
                .eq(landRetreat.getStatus() != null, "c.status", landRetreat.getStatus())
                .eq(StringUtils.isNotBlank(landRetreat.getRetreatApplicant()), "c.retreat_applicant", landRetreat.getRetreatApplicant())
                .eq(loginUserId != null, "c.add_user", loginUserId)
                .eq(landRetreat.getId() != null, "c.id", landRetreat.getId())
                ;

        return landRetreatMapper.selectCustomPage(new Page<>(landRetreat.getPage(), landRetreat.getPageSize()),eq);

    }

    /**
     * 退地详情
     * @param id
     * @return
     */
    @Override
    public LandRetreat findById(Long id) {
//        LandRetreat landRetreat = baseMapper.selectById(id);
//        if (landRetreat == null) {
//            return null;
//        }
        LandRetreat landRetreat = new LandRetreat();
        landRetreat.setId(id);
        landRetreat.setPage(0L);
        landRetreat.setPageSize(1L);
        Page<LandRetreat> landRetreatPage = this.listPage(landRetreat);
        landRetreat = landRetreatPage.getRecords().get(0);

        List<LandRetreatFile> landRetreatFiles = landRetreatFileMapper.selectList(
                Wrappers.lambdaQuery(LandRetreatFile.class)
                        .eq(LandRetreatFile::getLandRetreatId, landRetreat.getRetreatId())

        );
        landRetreat.setLandRetreatFileList(landRetreatFiles);
        return landRetreat;
    }

}
