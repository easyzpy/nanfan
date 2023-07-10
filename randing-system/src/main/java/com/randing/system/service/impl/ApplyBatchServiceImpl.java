package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.system.domain.po.ApplyBatch;
import com.randing.system.domain.vo.ApplyBatchVo;
import com.randing.system.mapper.ApplyBatchMapper;
import com.randing.system.service.IApplyBatchService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2022-12-21
 */
@Service
public class ApplyBatchServiceImpl extends ServiceImpl<ApplyBatchMapper, ApplyBatch> implements IApplyBatchService {

    @Override
    public List<ApplyBatchVo> getList(Integer isActive) {
        List<ApplyBatch> applyBatches = baseMapper.selectList(Wrappers.lambdaQuery(ApplyBatch.class).eq(isActive!=null, ApplyBatch::getActivity, isActive).orderByDesc(ApplyBatch::getId));
        ArrayList<ApplyBatchVo> reList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(applyBatches)) {
            for (ApplyBatch applyBatch : applyBatches) {
                ApplyBatchVo applyBatchVo = new ApplyBatchVo();
                BeanUtils.copyProperties(applyBatch, applyBatchVo);
                reList.add(applyBatchVo);
            }
        }
        return reList;
    }
}
