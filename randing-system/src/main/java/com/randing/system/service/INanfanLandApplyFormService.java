package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.vo.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;

/**
 * <p>
 * 崖州湾实验室南繁配套用地申请表 服务类
 * </p>
 *
 * @author Leen
 * @since 2022-12-22
 */
public interface INanfanLandApplyFormService extends IService<NanfanLandApplyForm> {

    Page<NanfanLandApplyFormVo> getKeepApplay(KeepApplyReqDTO keepApplyReqDTO);

    NanfanLandApplyFormVo findById(Long id);

    /**
     * 待审核列表
     * @param keepApplyReqDTO
     * @return
     */
    Page<NanfanLandApplyFormVo> getPushApply(KeepApplyReqDTO keepApplyReqDTO);

    /**
     * 已审核列表
     * @param keepApplyReqDTO
     * @return
     */
    Page<NanfanLandApplyFormVo> getAdoptApplay(KeepApplyReqDTO keepApplyReqDTO);

    /**
     * 已退回列表
     * @param keepApplyReqDTO
     * @return
     */
    Page<NanfanLandApplyFormVo> getfailApplay(KeepApplyReqDTO keepApplyReqDTO);

    Page<NanfanLandApplyFormVo> AllApproApply(KeepApplyReqDTO keepApplyReqDTO);

    NanfanLandApplyFormVo getApplyDetail(Long id);
}
