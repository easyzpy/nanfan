package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.randing.system.domain.vo.base.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import com.randing.system.mapper.NanfanLandApplyFormMapper;
import com.randing.system.service.INanfanLandApplyFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 崖州湾实验室南繁配套用地申请表 服务实现类
 * </p>
 *
 * @author Leen
 * @since 2022-12-22
 */
@Service
public class NanfanLandApplyFormServiceImpl extends ServiceImpl<NanfanLandApplyFormMapper, NanfanLandApplyForm> implements INanfanLandApplyFormService {

    @Autowired
    private NanfanLandApplyFormMapper nanfanLandApplyFormMapper;
    @Override
    public Page<NanfanLandApplyFormVo> getKeepApplay(KeepApplyReqDTO keepApplyReqDTO) {
//        Page<NanfanLandApplyForm> nanfanLandApplyFormPage = baseMapper.selectPage(new Page<>(keepApplyReqDTO.getPage(), keepApplyReqDTO.getPageSize()), Wrappers.lambdaQuery(NanfanLandApplyForm.class)
////                        .and(StringUtils.isNotBlank(nanfanLandApplyFormVo.getText()),and->{
////                            and
////                                    .like(NanfanLandApplyForm::getLandApplyUnit, nanfanLandApplyFormVo.getText())
////                                    .or()
////                                    .like(NanfanLandApplyForm::getLandApplyPurpose, nanfanLandApplyFormVo.getText());
////                                    //TODO 作物类型
//////                                    .or()
//////                                    .like(NanfanLandApplyForm::getLandApplyUnit, nanfanLandApplyFormVo.getText())
////                        })
//                .eq(StringUtils.isNotBlank(keepApplyReqDTO.getBatchId()), NanfanLandApplyForm::getBatchId, keepApplyReqDTO.getBatchId())
//                .eq(keepApplyReqDTO.getDelFlag() != null, NanfanLandApplyForm::getDelFlag, keepApplyReqDTO.getDelFlag())
//                .orderBy(keepApplyReqDTO.getOrderName()!=null && keepApplyReqDTO.getOrderName().equals("budget"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getBudget)
//                .orderBy(keepApplyReqDTO.getOrderName()!=null && keepApplyReqDTO.getOrderName().equals("landApplyArea"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getLandApplyArea)
//                .orderBy(keepApplyReqDTO.getOrderName()!=null && keepApplyReqDTO.getOrderName().equals("createTime"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getCreateTime)
//        );
//        return nanfanLandApplyFormPage;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Wrapper<NanfanLandApplyForm> wrapper = Wrappers.lambdaQuery(NanfanLandApplyForm.class)
                .eq(StringUtils.isNotBlank(keepApplyReqDTO.getLandApplyUnit()), NanfanLandApplyForm::getLandApplyUnit, keepApplyReqDTO.getLandApplyUnit())
                .eq(StringUtils.isNotBlank(keepApplyReqDTO.getBatchId()), NanfanLandApplyForm::getBatchId, keepApplyReqDTO.getBatchId())
                .eq(keepApplyReqDTO.getDelFlag() != null, NanfanLandApplyForm::getDelFlag, keepApplyReqDTO.getDelFlag())
                //TODO 过滤创建者 暂时不加 不然没有数据了
//                .eq()
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("budget"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getBudget)
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("landApplyArea"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getLandApplyArea)
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("createTime"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getCreateTime);
        return nanfanLandApplyFormMapper.getKeepApplay(new Page<>(keepApplyReqDTO.getPage(), keepApplyReqDTO.getPageSize()), keepApplyReqDTO, wrapper);
    }
    @Override
    public NanfanLandApplyFormVo findById(Long id) {
        NanfanLandApplyForm nanfanLandApplyForm = baseMapper.selectById(id);
        NanfanLandApplyFormVo nanfanLandApplyFormVo = new NanfanLandApplyFormVo();
        BeanUtils.copyProperties(nanfanLandApplyForm, nanfanLandApplyFormVo);
        return nanfanLandApplyFormVo;
    }
}
