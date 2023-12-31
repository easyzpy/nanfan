package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandRetreat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
public interface ILandRetreatService extends IService<LandRetreat> {

    Page<LandRetreat> listPage(LandRetreat landRetreat);

    LandRetreat findById(Long id);
}
