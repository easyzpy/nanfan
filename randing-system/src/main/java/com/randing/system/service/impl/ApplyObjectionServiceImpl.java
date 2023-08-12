package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.LoginUser;
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
        Long loginUserId = LoginUser.getLoginUserId();

        QueryWrapper<ApplyObjection> wrapper = Wrappers.query();
        if (applyObjectionReqDTO != null) {
            wrapper.eq(applyObjectionReqDTO.getType() != null, "c.objection_type", applyObjectionReqDTO.getType());
            wrapper.eq(applyObjectionReqDTO.getBatchId() != null, "d.batch_id", applyObjectionReqDTO.getBatchId());
            wrapper.eq(loginUserId != null, "c.user_id", loginUserId);
        }
        wrapper.orderByDesc("c.add_time");
        return applyObjectionMapper.getList(new Page(applyObjectionReqDTO.getPage(), applyObjectionReqDTO.getPageSize()), wrapper);
    }

}
