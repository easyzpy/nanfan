package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.system.domain.po.FieldBreedServiceApplyForm;
import com.randing.system.domain.vo.FieldBreedServiceApplyFormVo;
import com.randing.system.mapper.FieldBreedServiceApplyFormMapper;
import com.randing.system.service.IFieldBreedServiceApplyFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 崖州湾实验室用地田间，育种服务申请表 服务实现类
 * </p>
 *
 * @author Leen
 * @since 2022-12-17
 */
@Service
public class FieldBreedServiceApplyFormServiceImpl extends ServiceImpl<FieldBreedServiceApplyFormMapper, FieldBreedServiceApplyForm> implements IFieldBreedServiceApplyFormService {

    @Resource
    private FieldBreedServiceApplyFormMapper fieldBreedServiceApplyFormMapper;

    @Override
    public Page<FieldBreedServiceApplyForm> listPage(FieldBreedServiceApplyFormVo fieldBreedServiceApplyFormVo) {
        LambdaQueryWrapper<FieldBreedServiceApplyForm> wrapper = Wrappers.lambdaQuery(FieldBreedServiceApplyForm.class);
        return baseMapper.selectPage(new Page<>(fieldBreedServiceApplyFormVo.getPage(), fieldBreedServiceApplyFormVo.getPageSize()), wrapper);
    }


    @Override
    public FieldBreedServiceApplyFormVo findById(Long id) {
        FieldBreedServiceApplyForm FieldBreedServiceApplyForm = baseMapper.selectById(id);
        FieldBreedServiceApplyFormVo FieldBreedServiceApplyFormVo = new FieldBreedServiceApplyFormVo();
        BeanUtils.copyProperties(FieldBreedServiceApplyForm, FieldBreedServiceApplyFormVo);
        return FieldBreedServiceApplyFormVo;
    }


}
