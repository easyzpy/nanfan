package com.randing.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.randing.system.domain.vo.base.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 崖州湾实验室南繁配套用地申请表 Mapper 接口
 * </p>
 *
 * @author Leen
 * @since 2022-12-22
 */
public interface NanfanLandApplyFormMapper extends BaseMapper<NanfanLandApplyForm> {

    Page<NanfanLandApplyFormVo> getKeepApplay(IPage page, @Param("dto") KeepApplyReqDTO keepApplyReqDTO, @Param(Constants.WRAPPER) Wrapper wrapper);

}
