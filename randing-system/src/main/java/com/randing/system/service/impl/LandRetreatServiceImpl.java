package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.system.domain.po.LandRetreat;
import com.randing.system.domain.po.LandRetreatFile;
import com.randing.system.mapper.LandRetreatFileMapper;
import com.randing.system.mapper.LandRetreatMapper;
import com.randing.system.service.ILandRetreatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private LandRetreatFileMapper landRetreatFileMapper;
    @Override
    public List<LandRetreat> listPage(LandRetreat landRetreat) {
        return baseMapper.selectList(Wrappers.lambdaQuery(LandRetreat.class)
                .eq(LandRetreat::getStatus, landRetreat.getStatus())
                .eq(LandRetreat::getRetreatApplicant, landRetreat.getRetreatApplicant())

        );

    }

    /**
     * 退地详情
     * @param id
     * @return
     */
    @Override
    public LandRetreat findById(Long id) {
        LandRetreat landRetreat = baseMapper.selectById(id);
        if (landRetreat == null) {
            return null;
        }
        List<LandRetreatFile> landRetreatFiles = landRetreatFileMapper.selectList(
                Wrappers.lambdaQuery(LandRetreatFile.class)
                        .eq(LandRetreatFile::getLandRetreatId, landRetreat.getRetreatId())

        );
        landRetreat.setLandRetreatFileList(landRetreatFiles);
        return landRetreat;
    }

}
