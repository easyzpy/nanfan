package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyObjection;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;
import com.randing.system.mapper.ApplyObjectionMapper;
import com.randing.system.service.IApplyObjectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-02-08
 */
@Service
public class ApplyObjectionServiceImpl extends ServiceImpl<ApplyObjectionMapper, ApplyObjection> implements IApplyObjectionService {

    @Resource
    private ApplyObjectionMapper applyObjectionMapper;

    @Override
    public Page<ApplyObjection> getList(ApplyObjectionReqDTO applyObjectionReqDTO) {
//        List<ApplyObjection> applyBatches = baseMapper.selectList(Wrappers.emptyWrapper());
        LambdaQueryWrapper<ApplyObjection> wrapper = Wrappers.lambdaQuery(ApplyObjection.class);
        if (applyObjectionReqDTO != null) {
            wrapper.eq(applyObjectionReqDTO.getType() != null, ApplyObjection::getObjectionType, applyObjectionReqDTO.getType());
        }
        return applyObjectionMapper.getList(new Page(applyObjectionReqDTO.getPage(), applyObjectionReqDTO.getPageSize()), wrapper);
    }

}
