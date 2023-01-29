package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandContract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.LandInforVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
public interface ILandContractService extends IService<LandContract> {

    Page<LandContract> listPage(LandContract landContract);

    LandContract findById(Long id);
}
