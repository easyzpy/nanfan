package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.LoginUser;
import com.randing.system.domain.po.LandContract;
import com.randing.system.domain.po.LandContractContent;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.mapper.LandContractContentMapper;
import com.randing.system.mapper.LandContractMapper;
import com.randing.system.service.ILandContractContentService;
import com.randing.system.service.ILandContractService;
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
 * @since 2023-01-15
 */
@Service
public class LandContractServiceImpl extends ServiceImpl<LandContractMapper, LandContract> implements ILandContractService {

    @Autowired
    private LandContractContentMapper landContractContentMapper;
    @Override
    public Page<LandContract> listPage(LandContract landContract) {
        Long loginUserId = LoginUser.getLoginUserId();
        Page<LandContract> landContractPage = baseMapper.selectPage(new Page<>(landContract.getPage(), landContract.getPageSize())
                , Wrappers.lambdaQuery(LandContract.class)
                        .eq(StringUtils.isNotBlank(landContract.getContractId()), LandContract::getContractStatus, landContract.getContractId())
                        //搜索的都是生效合同
                        .in(LandContract::getContractStatus, 1, 2)
                        .like(StringUtils.isNotBlank(landContract.getNailName()), LandContract::getNailName, landContract.getNailName())
                        .like(StringUtils.isNotBlank(landContract.getNailName()), LandContract::getSecondName, landContract.getNailName())
                        .eq(loginUserId!=null, LandContract::getAddUser, loginUserId)
                        .orderByAsc(LandContract::getContractStatus)
        );

        return landContractPage;
    }

    @Override
    public LandContract findById(Long id) {
        LandContract landContract = baseMapper.selectById(id);
        List<LandContractContent> landContractContents = landContractContentMapper.selectList(Wrappers.lambdaQuery(LandContractContent.class).eq(LandContractContent::getContractId, landContract.getContractId()));
        landContract.setLandContractContentList(landContractContents);

        return landContract;
    }
}
