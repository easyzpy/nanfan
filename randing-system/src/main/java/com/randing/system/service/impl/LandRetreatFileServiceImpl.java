package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.system.domain.po.LandRetreatFile;
import com.randing.system.mapper.LandRetreatFileMapper;
import com.randing.system.service.ILandRetreatFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
@Service
public class LandRetreatFileServiceImpl extends ServiceImpl<LandRetreatFileMapper, LandRetreatFile> implements ILandRetreatFileService {

    @Override
    public List<LandRetreatFile> getArtificialById(String id, Integer fileType) {
        return baseMapper.selectList(Wrappers.lambdaQuery(LandRetreatFile.class)
                .eq(LandRetreatFile::getFileType, fileType).eq(LandRetreatFile::getLandRetreatId, id));

    }
}
