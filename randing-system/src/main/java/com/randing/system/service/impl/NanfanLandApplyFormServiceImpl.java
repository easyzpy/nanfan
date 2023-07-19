package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.ApplyBatch;
import com.randing.system.domain.po.LandApplyInfor;
import com.randing.system.domain.po.LandApplyOper;
import com.randing.system.domain.po.LandApplyScore;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.po.NanfanLandApplyForm;
import com.randing.system.domain.vo.ApplyBatchVo;
import com.randing.system.domain.vo.KeepApplyReqDTO;
import com.randing.system.domain.vo.base.NanfanLandApplyFormVo;
import com.randing.system.domain.vo.base.NanfanLandApplyPostVo;
import com.randing.system.mapper.LandApplyInforMapper;
import com.randing.system.mapper.LandApplyOperMapper;
import com.randing.system.mapper.LandInforMapper;
import com.randing.system.mapper.NanfanLandApplyFormMapper;
import com.randing.system.service.IApplyBatchService;
import com.randing.system.service.ILandApplyScoreService;
import com.randing.system.service.INanfanLandApplyFormService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Autowired
    private IApplyBatchService applyBatchService;
    @Autowired
    private ILandApplyScoreService landApplyScoreService;
    @Override
    @Transactional
    public NanfanLandApplyFormVo landApply(NanfanLandApplyPostVo dto){
        boolean isEdit = false;
        if (dto.getId() != null) {
            isEdit = true;
        }
        if (dto.getDelFlag() == null || dto.getDelFlag() != 2 && dto.getDelFlag() != 3) {
            throw new BaseException("状态标识异常");
        }
        //需要验证<批次> 和 <批次日期和地块出租日期是否对应>
        ApplyBatch byId = applyBatchService.getOne(Wrappers.lambdaQuery(ApplyBatch.class).eq(ApplyBatch::getBatchId, dto.getBatchId()));
        if (byId == null) {
            throw new BaseException("所属批次不能为空");
        }
        //根据本年科研计划需求，租地时间是否能足够 如果选择否 则需要验证延长时间
        if (dto.getTimeEnough() != 0 && dto.getTimeEnough() != 1) {
            throw new BaseException("根据本年科研计划需求，租地时间是否能足够不能为空");
        }
        if (dto.getTimeEnough() == 0 && dto.getTimeEnoughTime() == null) {
            throw new BaseException("满足本年科研计划需要延长租地(天)不能为空");
        }
        if (CollectionUtils.isEmpty(dto.getApplyInfors())) {
            throw new BaseException("第一优先不能为空");
        }
        //判断批次是否可用
        List<ApplyBatchVo> list = applyBatchService.getList(1);
        if (CollectionUtils.isEmpty(list)) {
            throw new BaseException("无可用批次");
        }
        boolean flag = false;
        for (ApplyBatchVo f : list) {
            if (f.getBatchId().equals(dto.getBatchId())) {
                flag = true;
            }
        }

        if (!flag) {
            throw new BaseException("当前批次不可用");
        }
        List<LandInfor> applyInfors = dto.getApplyInfors();
        applyInfors.forEach(ai->{
            if (ai.getId() == null) {
                throw new BaseException("参数错误");
            }
        });
        List<Long> collect = applyInfors.stream().map(LandInfor::getId).collect(Collectors.toList());
        List<LandInfor> landInfors = landInforMapper.selectList(Wrappers.lambdaQuery(LandInfor.class).in(LandInfor::getId, collect));
        if (landInfors.size() != collect.size()) {
            throw new BaseException("地块选择错误");
        }
        landInfors.forEach(f->{
            if (!f.getLandType().equals("0")) {
                throw new BaseException("选择土地必须为可申请状态");
            }
//            String landCropType = f.getLandCropType();
//            if (landCropType == null) {
//                throw new BaseException(f.getLandName() + "地块无合适的作物类型");
//            }
//            boolean contains = landCropType.contains(dto.getLandApplyType().trim());
            //万总说不用校验这个
//            if (!contains) {
//                throw new BaseException(f.getLandName() + "地块作物类型不匹配");
//            }
            //校验土地<可用面积> 和<申请面积>
            //土地可用面积
            Double landAreaSurplus = f.getLandAreaSurplus();
            //申请面积
            Double landApplyArea = dto.getLandApplyArea();
            if (landAreaSurplus < landApplyArea) {
                //申请面积大于土地可用面积
                throw new BaseException(f.getLandName() + "可用面积小于申请面积");
            }
        });
        String landApplyUnitScore = dto.getLandApplyUnitScore();
        if (landApplyUnitScore !=null
                && !landApplyUnitScore.equals("20")
                && !landApplyUnitScore.equals("15")
                && !landApplyUnitScore.equals("10")
        ) {
            throw new BaseException("请选择正确的单位类型");
        }
        String researchDirectionsScore = dto.getResearchDirectionsScore();
        if (researchDirectionsScore !=null
                && !researchDirectionsScore.equals("25")
                && !researchDirectionsScore.equals("22")
                && !researchDirectionsScore.equals("20")
                && !researchDirectionsScore.equals("18")
                && !researchDirectionsScore.equals("16")
        ) {
            throw new BaseException("请选择正确的科研项目级别");
        }
        String startResearchActivityScore = dto.getStartResearchActivityScore();
        if (startResearchActivityScore !=null
                && !startResearchActivityScore.equals("20")
                && !startResearchActivityScore.equals("18")
                && !startResearchActivityScore.equals("16")
                && !startResearchActivityScore.equals("16.1")
                && !startResearchActivityScore.equals("15")
        ) {
            throw new BaseException("请选择正确的开展南繁活动类型");
        }

        String landQuitLandRestoreScore = dto.getLandQuitLandRestoreScore();
        if (landQuitLandRestoreScore !=null
                && !landQuitLandRestoreScore.equals("15")
                && !landQuitLandRestoreScore.equals("10")
                && !landQuitLandRestoreScore.equals("5")
                && !landQuitLandRestoreScore.equals("2")
                && !landQuitLandRestoreScore.equals("-10")
        ) {
            throw new BaseException("请选择正确的用地退出土地恢复");
        }

        String driveLandGrowScore = dto.getDriveLandGrowScore();
        if (driveLandGrowScore !=null
                && !driveLandGrowScore.equals("15")
                && !driveLandGrowScore.equals("10")
                && !driveLandGrowScore.equals("10.1")
                && !driveLandGrowScore.equals("5")
        ) {
            throw new BaseException("请选择正确的带动当地发展");
        }
        NanfanLandApplyForm nanfanLandApplyForm = new NanfanLandApplyForm();
        BeanUtils.copyProperties(dto,nanfanLandApplyForm);
        nanfanLandApplyForm.setCreateUser(LoginUser.getId().intValue());
        /*
        计算总分 start
         */
        BigDecimal sum = new BigDecimal("0");
        if (researchDirectionsScore != null) {
            sum = sum.add(new BigDecimal(landApplyUnitScore));
        }
        if (researchDirectionsScore != null) {
            sum = sum.add(new BigDecimal(researchDirectionsScore));
        }
        if (startResearchActivityScore != null) {
            sum = sum.add(new BigDecimal(startResearchActivityScore));
        }
        if (landQuitLandRestoreScore != null) {
            sum = sum.add(new BigDecimal(landQuitLandRestoreScore));
        }
        if (driveLandGrowScore != null) {
            sum = sum.add(new BigDecimal(driveLandGrowScore));
        }
        nanfanLandApplyForm.setSumScore(sum.doubleValue());
        /*
        计算总分 end
         */
        //提交时间
        //2 保存不提交 3 提交（待审批）
        if (dto.getDelFlag() == 3) {
            nanfanLandApplyForm.setPushTime(LocalDateTime.now());
        }
        //申请用地地址 使用第一优先地址
        nanfanLandApplyForm.setLandApplyAddress(landInfors.get(0).getLandName());
        this.saveOrUpdate(nanfanLandApplyForm);
        //保存优先级表
        if (isEdit) {
            landApplyInforMapper.delete(Wrappers.lambdaQuery(LandApplyInfor.class).eq(LandApplyInfor::getApplyId, nanfanLandApplyForm.getId().intValue()));
        }
        LandApplyInfor landApplyInfor = new LandApplyInfor();
        for (LandInfor landInfor : landInfors) {
            landApplyInfor.setApplyId(nanfanLandApplyForm.getId().intValue());
            landApplyInfor.setInforId(landInfor.getId().intValue());
            landApplyInforMapper.insert(landApplyInfor);
        }
        //保存评分
        LandApplyScore landApplyScore = new LandApplyScore();
        BeanUtils.copyProperties(dto, landApplyScore);
        landApplyScore.setApplyFormId(nanfanLandApplyForm.getId().intValue());
        if (isEdit) {
            landApplyScoreService.remove(Wrappers.lambdaQuery(LandApplyScore.class).eq(LandApplyScore::getApplyFormId, nanfanLandApplyForm.getId().intValue()));
        }
        landApplyScoreService.save(landApplyScore);
        //保存操作申请操作表
        LandApplyOper landApplyOper = new LandApplyOper();
        landApplyOper.setApplyFormId(nanfanLandApplyForm.getId().intValue());
        landApplyOper.setOperType("用地申请");
        landApplyOper.setOperContent("保存用地申请");
        landApplyOper.setOperTime(LocalDateTime.now());
        landApplyOperMapper.insert(landApplyOper);
        //第一第二第三优先校验
        return null;
    }

}
