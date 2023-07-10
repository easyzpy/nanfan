package com.randing.system.service;

import com.randing.system.domain.po.Unit;
import com.randing.system.domain.po.UnitFile;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
public interface IUnitFileService extends IService<UnitFile> {

    void saveUnitFile(Unit unit);
}
