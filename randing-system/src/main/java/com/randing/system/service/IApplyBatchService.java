package com.randing.system.service;

import com.randing.system.domain.po.ApplyBatch;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.ApplyBatchVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2022-12-21
 */
public interface IApplyBatchService extends IService<ApplyBatch> {

    List<ApplyBatchVo> getList();
}
