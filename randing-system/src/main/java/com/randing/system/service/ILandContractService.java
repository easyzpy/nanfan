package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandContract;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.LandContractDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
public interface ILandContractService extends IService<LandContract> {

    Page<LandContractDTO> listPage(LandContract landContract);

    LandContract findById(Long id);
}
