package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyObjection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-02-08
 */
public interface IApplyObjectionService extends IService<ApplyObjection> {

    Page<ApplyObjection> getList(ApplyObjectionReqDTO applyObjectionReqDTO);
}
