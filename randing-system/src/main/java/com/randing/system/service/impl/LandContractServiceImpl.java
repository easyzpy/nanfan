package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.LoginUser;
import com.randing.system.domain.po.LandContract;
import com.randing.system.domain.po.LandContractContent;
import com.randing.system.domain.vo.LandContractDTO;
import com.randing.system.mapper.LandContractContentMapper;
import com.randing.system.mapper.LandContractMapper;
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
    private LandContractMapper landContractMapper;
    @Autowired
    private LandContractContentMapper landContractContentMapper;
    @Override
    public Page<LandContractDTO> listPage(LandContract landContract) {
        Long loginUserId = LoginUser.getLoginUserId();

        QueryWrapper<LandContract> wrapper = Wrappers.query();
            wrapper.eq(StringUtils.isNotBlank(landContract.getContractId()), "c.contract_status", landContract.getContractId())
                //搜索的都是生效合同
                .in("c.contract_status", 1, 2)
                .like(StringUtils.isNotBlank(landContract.getNailName()), "c.nail_name", landContract.getNailName())
                .like(StringUtils.isNotBlank(landContract.getNailName()), "c.second_name", landContract.getNailName())
                //合同通过applyUserId区分
                .eq(loginUserId != null, "c.apply_user_id", loginUserId)
                .orderByAsc("c.contract_status");
        Page<LandContractDTO> landContractPage = landContractMapper.getList(new Page<>(landContract.getPage(), landContract.getPageSize())
                ,wrapper
        );
        return landContractPage;
    }

    @Override
    public LandContract findById(Long id) {
        LandContract landContract = baseMapper.selectById(id);
        List<LandContractContent> landContractContents = landContractContentMapper
                .selectList(Wrappers.lambdaQuery(LandContractContent.class)
                        .eq(LandContractContent::getContractId, landContract.getContractId()));
        landContract.setLandContractContentList(landContractContents);

        return landContract;
    }
}
