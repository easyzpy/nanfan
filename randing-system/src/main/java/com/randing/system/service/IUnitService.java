package com.randing.system.service;

import com.randing.system.domain.po.Unit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
public interface IUnitService extends IService<Unit> {

    List<Unit> findByLikeUnitName(Unit unit);
}
