package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.FieldBreedServiceApplyForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.FieldBreedServiceApplyFormVo;

/**
 * <p>
 * 崖州湾实验室用地田间，育种服务申请表 服务类
 * </p>
 *
 * @author Leen
 * @since 2022-12-17
 */
public interface IFieldBreedServiceApplyFormService extends IService<FieldBreedServiceApplyForm> {

    Page<FieldBreedServiceApplyForm> listPage(FieldBreedServiceApplyFormVo fieldBreedServiceApplyFormVo);

    FieldBreedServiceApplyFormVo findById(Long id);
}
