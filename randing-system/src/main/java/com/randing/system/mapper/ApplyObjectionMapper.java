package com.randing.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyObjection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Leen
 * @since 2023-02-08
 */
public interface ApplyObjectionMapper extends BaseMapper<ApplyObjection> {

    public Page<ApplyObjection> getList(IPage page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
