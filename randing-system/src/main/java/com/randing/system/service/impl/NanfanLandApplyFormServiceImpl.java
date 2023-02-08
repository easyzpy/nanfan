package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandApplyInfor;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.randing.system.domain.vo.base.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import com.randing.system.mapper.LandApplyInforMapper;
import com.randing.system.mapper.LandInforMapper;
import com.randing.system.mapper.NanfanLandApplyFormMapper;
import com.randing.system.service.INanfanLandApplyFormService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Resource
    private LandApplyInforMapper landApplyInforMapper;
    @Resource
    private LandInforMapper landInforMapper;
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
                .in(!CollectionUtils.isEmpty(keepApplyReqDTO.getDelFlagArr()), NanfanLandApplyForm::getDelFlag, keepApplyReqDTO.getDelFlagArr())
                //TODO 过滤创建者 暂时不加 不然没有数据了
//                .eq()
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("budget"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getBudget)
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("landApplyArea"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getLandApplyArea)
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("createTime"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, NanfanLandApplyForm::getCreateTime);
        Page<NanfanLandApplyFormVo> keepApplay = nanfanLandApplyFormMapper.getKeepApplay(new Page<>(keepApplyReqDTO.getPage(), keepApplyReqDTO.getPageSize()), keepApplyReqDTO, wrapper);
        if (!CollectionUtils.isEmpty(keepApplay.getRecords())) {
            List<Long> applyIds = keepApplay.getRecords().stream().map(NanfanLandApplyFormVo::getId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(applyIds)) {
                List<LandApplyInfor> landApplyInfors = landApplyInforMapper.selectList(Wrappers.lambdaQuery(LandApplyInfor.class).in(LandApplyInfor::getApplyId, applyIds));
                if (!CollectionUtils.isEmpty(landApplyInfors)) {
                    List<Integer> landInfoIds = landApplyInfors.stream().map(LandApplyInfor::getInforId).collect(Collectors.toList());
                    List<LandInfor> landInfors = landInforMapper.selectList(Wrappers.lambdaQuery(LandInfor.class).in(LandInfor::getId, landInfoIds));
                    Map<Integer, List<LandApplyInfor>> applyIdAndInfoId = landApplyInfors.stream().collect(Collectors.groupingBy(LandApplyInfor::getApplyId));
                    Map<Long, LandInfor> landIdAndLand = landInfors.stream().collect(Collectors.toMap(k -> k.getId(), v -> v));
                    //遍历塞值
                    for (NanfanLandApplyFormVo record : keepApplay.getRecords()) {
                        Long recordApplyId = record.getId();
                        List<LandApplyInfor> landApplyInfors1 = applyIdAndInfoId.get(Integer.parseInt(String.valueOf(recordApplyId)));
                        if (!landApplyInfors1.isEmpty()) {
                            List<LandInfor> landInfos = new ArrayList<>();
                            for (LandApplyInfor landApplyInfor : landApplyInfors1) {
                                LandInfor landInfor = landIdAndLand.get(Long.parseLong(String.valueOf(landApplyInfor.getInforId())));
                                if (landInfor!=null) {
                                    landInfos.add(landInfor);
                                }
                            }
                            record.setApplyInfors(landInfors);
                        }
                    }
                }
            }

        }
        return keepApplay;
    }
    @Override
    public NanfanLandApplyFormVo findById(Long id) {
        NanfanLandApplyForm nanfanLandApplyForm = baseMapper.selectById(id);
        NanfanLandApplyFormVo nanfanLandApplyFormVo = new NanfanLandApplyFormVo();
        BeanUtils.copyProperties(nanfanLandApplyForm, nanfanLandApplyFormVo);
        return nanfanLandApplyFormVo;
    }

    @Override
    public Page<NanfanLandApplyFormVo> getPushApply(KeepApplyReqDTO keepApplyReqDTO) {
        keepApplyReqDTO.setDelFlag("3");
        return this.getKeepApplay(keepApplyReqDTO);
    }
    @Override
    public Page<NanfanLandApplyFormVo> getAdoptApplay(KeepApplyReqDTO keepApplyReqDTO) {
        keepApplyReqDTO.setDelFlag("4");
        return this.getKeepApplay(keepApplyReqDTO);
    }
    @Override
    public Page<NanfanLandApplyFormVo> getfailApplay(KeepApplyReqDTO keepApplyReqDTO) {
        keepApplyReqDTO.setDelFlag("5");
        return this.getKeepApplay(keepApplyReqDTO);
    }
    @Override
    public Page<NanfanLandApplyFormVo> AllApproApply(KeepApplyReqDTO keepApplyReqDTO) {
        keepApplyReqDTO.setDelFlagArr(Arrays.asList("3","4","5"));
        return this.getKeepApplay(keepApplyReqDTO);
    }
}
