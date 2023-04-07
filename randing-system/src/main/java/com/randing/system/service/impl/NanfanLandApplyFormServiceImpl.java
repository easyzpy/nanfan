package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandApplyInfor;
import com.randing.system.domain.po.LandApplyOper;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.randing.system.domain.vo.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import com.randing.system.mapper.LandApplyInforMapper;
import com.randing.system.mapper.LandApplyOperMapper;
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
    @Resource
    private LandApplyOperMapper landApplyOperMapper;
    @Override
    public Page<NanfanLandApplyFormVo> getKeepApplay(KeepApplyReqDTO keepApplyReqDTO) {
        Long loginUserId = LoginUser.getLoginUserId();
        QueryWrapper<Object> wrapper = Wrappers.query()
                .like(StringUtils.isNotBlank(keepApplyReqDTO.getLandApplyUnit()), "c.land_apply_unit", keepApplyReqDTO.getLandApplyUnit())
                .eq(StringUtils.isNotBlank(keepApplyReqDTO.getBatchId()), "c.batch_id", keepApplyReqDTO.getBatchId())
                .eq(keepApplyReqDTO.getDelFlag() != null, "c.del_flag", keepApplyReqDTO.getDelFlag())
                .in(!CollectionUtils.isEmpty(keepApplyReqDTO.getDelFlagArr()), "c.del_flag", keepApplyReqDTO.getDelFlagArr())
                .eq(keepApplyReqDTO.getId() != null, "c.id", keepApplyReqDTO.getId())
                .like(keepApplyReqDTO.getLandApplyPurpose() != null, "c.land_apply_purpose", keepApplyReqDTO.getLandApplyPurpose())
//                //过滤创建者 暂时不加 不然没有数据了
                .eq(loginUserId != null, "c.create_user", loginUserId)
//                .eq("g.oper_type", "用地审批")
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("budget"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, "c.budget")
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("landApplyArea"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, "c.land_apply_area")
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("createTime"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, "c.create_time")
                .orderBy(keepApplyReqDTO.getOrderName() != null && keepApplyReqDTO.getOrderName().equals("sumScore"), keepApplyReqDTO.getOrderType() == OrderByEnum.asc, "c.sum_score")
                .orderByDesc(keepApplyReqDTO.getOrderName() == null, "c.create_time")
                ;


        Page<NanfanLandApplyFormVo> keepApplay = nanfanLandApplyFormMapper.getKeepApplay(new Page<>(keepApplyReqDTO.getPage(), keepApplyReqDTO.getPageSize()), keepApplyReqDTO, wrapper);
        if (!CollectionUtils.isEmpty(keepApplay.getRecords())) {
            List<Long> applyIds = keepApplay.getRecords().stream().map(NanfanLandApplyFormVo::getId).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(applyIds)) {
                //处理landInfo
                List<LandApplyInfor> landApplyInfors = landApplyInforMapper.selectList(
                        Wrappers.lambdaQuery(LandApplyInfor.class).in(LandApplyInfor::getApplyId, applyIds)
                                .isNotNull(LandApplyInfor::getInforId)
                );
                //处理审批时间
//                applyIds

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
                            record.setApplyInfors(landInfos);
                        }
                    }
                }

                List<LandApplyOper> opers = landApplyOperMapper.selectList(Wrappers.lambdaQuery(LandApplyOper.class)
//                        .eq(LandApplyOper::getOperType, "用地审批")
                        .in(LandApplyOper::getApplyFormId, applyIds)
                        .orderByDesc(LandApplyOper::getOperTime)
                );
                if (!CollectionUtils.isEmpty(opers)) {
                    Map<Integer, List<LandApplyOper>> collect = opers.stream().collect(Collectors.groupingBy(LandApplyOper::getApplyFormId));

                    //遍历塞值
                    for (NanfanLandApplyFormVo record : keepApplay.getRecords()) {
                        Long recordApplyId = record.getId();
                        List<LandApplyOper> landApplyOpers = collect.get(Integer.parseInt(String.valueOf(recordApplyId)));
                        if (!landApplyOpers.isEmpty()) {
                            for (int i = 0; i < (Math.min(landApplyOpers.size(), 2)); i++) {
                                LandApplyOper landApplyOper = landApplyOpers.get(i);
                                if (landApplyOper.getOperType().equals("用地审批")) {
                                    record.setOperContent(landApplyOper.getOperContent());
                                    record.setOperType(landApplyOper.getOperType());
                                    record.setOperTime(landApplyOper.getOperTime());
                                } else {
                                    record.setOperContentApply(landApplyOper.getOperContent());
                                    record.setOperTypeApply(landApplyOper.getOperType());
                                    record.setOperTimeApply(landApplyOper.getOperTime());
                                }
                            }
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

    @Override
    public NanfanLandApplyFormVo getApplyDetail(Long id) {
        KeepApplyReqDTO keepApplyReqDTO = new KeepApplyReqDTO();
        keepApplyReqDTO.setId(id);
        keepApplyReqDTO.setPage(1L);
        keepApplyReqDTO.setPageSize(1L);
        Page<NanfanLandApplyFormVo> keepApplay = this.getKeepApplay(keepApplyReqDTO);
        if (CollectionUtils.isEmpty(keepApplay.getRecords())) {
            return null;
        }
        return keepApplay.getRecords().get(0);
    }

}
