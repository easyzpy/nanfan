package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.common.utils.iface.dto.YzbUserInfo;
import com.randing.system.domain.po.SelfExamination;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationGainCheck;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;
import com.randing.system.mapper.SelfExaminationMapper;
import com.randing.system.service.ISelfExaminationActivityService;
import com.randing.system.service.ISelfExaminationBaseService;
import com.randing.system.service.ISelfExaminationGainCheckService;
import com.randing.system.service.ISelfExaminationGainService;
import com.randing.system.service.ISelfExaminationPermanentService;
import com.randing.system.service.ISelfExaminationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Service
public class SelfExaminationServiceImpl extends ServiceImpl<SelfExaminationMapper, SelfExamination> implements ISelfExaminationService {

    @Autowired
    private ISelfExaminationPermanentService selfExaminationPermanentService;
    @Autowired
    private ISelfExaminationBaseService selfExaminationBaseService;
    @Autowired
    private ISelfExaminationActivityService selfExaminationActivityService;
    @Autowired
    private ISelfExaminationGainService selfExaminationGainService;
    @Autowired
    private ISelfExaminationGainCheckService selfExaminationGainCheckService;
    @Override
    @Transactional
    public int step1save(SelfStep1ReqVo reqVo) {
        YzbUserInfo userInfo = LoginUser.getUser().getUserInfo();
        String phone = userInfo.getPhone();
        SelfExamination selfExamination = new SelfExamination();
        BeanUtils.copyProperties(reqVo, selfExamination);
        SelfExamination dbSelfExamination = this.getSelfExamination();
        if (dbSelfExamination != null) {
            selfExamination.setId(dbSelfExamination.getId());
        }
        selfExamination.setContactPhone(phone);
        selfExamination.setSelfExaminationId(getUUID());
        saveOrUpdate(selfExamination);
        return 0;
    }

    /**
     * 常驻联系人可以添加多个
     * @param reqVo
     * @return
     */
    @Override
    @Transactional
    public int step2Permanent(Step2SelfExaminationPermanentReqVo reqVo) {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("清先保存基本信息");
        }
        SelfExaminationPermanent selfExaminationPermanent = new SelfExaminationPermanent();
        BeanUtils.copyProperties(reqVo, selfExaminationPermanent);
        selfExaminationPermanent.setPermanentId(getUUID());
        selfExaminationPermanent.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationPermanentService.save(selfExaminationPermanent);
        return 0;
    }

    @Override
    public List<SelfExaminationPermanent> step2GetPermanentList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationPermanent> list = selfExaminationPermanentService.list(Wrappers.lambdaQuery(SelfExaminationPermanent.class).eq(SelfExaminationPermanent::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        return list;
    }



    @Override
    @Transactional
    public int step3Base(Step3SelfExaminationBaseReqVo reqVo) {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("清先保存基本信息");
        }
        SelfExaminationBase selfExaminationBase = new SelfExaminationBase();
        BeanUtils.copyProperties(reqVo, selfExaminationBase);
        selfExaminationBase.setBaseId(getUUID());
        selfExaminationBase.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationBaseService.save(selfExaminationBase);
        return 0;
    }

    @Override
    public List<SelfExaminationBase> step3GetBasetList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationBase> list = selfExaminationBaseService.list(Wrappers.lambdaQuery(SelfExaminationBase.class).eq(SelfExaminationBase::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        return list;
    }

    @Override
    @Transactional
    public int step4Activity(Step4SelfExaminationActivityReqVo reqVo) {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("清先保存基本信息");
        }
        SelfExaminationActivity selfExaminationActivity = new SelfExaminationActivity();
        BeanUtils.copyProperties(reqVo, selfExaminationActivity);
        selfExaminationActivity.setActivityId(getUUID());
        selfExaminationActivity.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationActivityService.save(selfExaminationActivity);
        return 0;
    }

    @Override
    public List<SelfExaminationActivity> step4GetActivityList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationActivity> list = selfExaminationActivityService.list(Wrappers.lambdaQuery(SelfExaminationActivity.class).eq(SelfExaminationActivity::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        return list;
    }

    @Override
    public int step5Gain(Step5SelfExaminationGainReqVo reqVo) {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("清先保存基本信息");
        }
        SelfExaminationGain selfExaminationGain = new SelfExaminationGain();
        BeanUtils.copyProperties(reqVo, selfExaminationGain);
        selfExaminationGain.setGainId(getUUID());
        selfExaminationGain.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationGainService.save(selfExaminationGain);
        List<SelfExaminationGain> list = selfExaminationGainService.list(Wrappers.lambdaQuery(SelfExaminationGain.class).eq(SelfExaminationGain::getGainId, selfExaminationGain.getGainId()).eq(SelfExaminationGain::getSelfExaminationId, selfExamination.getSelfExaminationId()).last(" limit 1"));
        SelfExaminationGain selfExaminationGain1 = list.get(0);
        //保存check
        SelfExaminationGainCheck selfExaminationGainCheck = new SelfExaminationGainCheck();
        BeanUtils.copyProperties(reqVo, selfExaminationGainCheck);
        selfExaminationGainCheck.setGainId(selfExaminationGain1.getGainId());
        selfExaminationGainCheck.setCheckId(getUUID());
        selfExaminationGainCheckService.save(selfExaminationGainCheck);

        return 0;
    }

    @Override
    public List<SelfExaminationGain> step5GetGainList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationGain> list = selfExaminationGainService.list(Wrappers.lambdaQuery(SelfExaminationGain.class).eq(SelfExaminationGain::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        if (list != null) {
            List<String> collect = list.stream().map(SelfExaminationGain::getGainId).collect(Collectors.toList());
            if (!collect.isEmpty()) {
                List<SelfExaminationGainCheck> list1 = selfExaminationGainCheckService.list(Wrappers.lambdaQuery(SelfExaminationGainCheck.class).in(SelfExaminationGainCheck::getGainId, collect));
                Map<String, List<SelfExaminationGainCheck>> checkMap = list1.stream().collect(Collectors.groupingBy(SelfExaminationGainCheck::getGainId));
                for (SelfExaminationGain selfExaminationGain : list) {
                    List<SelfExaminationGainCheck> selfExaminationGainChecks = checkMap.get(selfExaminationGain.getGainId());
                    if (!CollectionUtils.isEmpty(selfExaminationGainChecks)) {
                        selfExaminationGain.setSelfExaminationGainCheck(selfExaminationGainChecks.get(0));
                    }
                }
            }
        }
        return list;
    }

    public SelfExaminationPermanent getExaminationPermanentByExaminationId(String examinationId) {
        if (examinationId == null) {
            return null;
        }
        List<SelfExaminationPermanent> selfExaminationPermanents = selfExaminationPermanentService.list(Wrappers.lambdaQuery(SelfExaminationPermanent.class).eq(SelfExaminationPermanent::getSelfExaminationId, examinationId));
        if (CollectionUtils.isEmpty(selfExaminationPermanents)) {
            return null;
        }
        return selfExaminationPermanents.get(0);
    }
    @Override
    public SelfExamination getSelfExamination() {
        YzbUserInfo userInfo = LoginUser.getUser().getUserInfo();
        String phone = userInfo.getPhone();
        if (StringUtils.isBlank(phone)) {
            throw new BaseException("手机号获取异常");
        }
        List<SelfExamination> selfExaminations = baseMapper.selectList(Wrappers.lambdaQuery(SelfExamination.class).eq(SelfExamination::getContactPhone, phone));
        if (CollectionUtils.isEmpty(selfExaminations)) {
            return null;
        }
        return selfExaminations.get(0);
    }

    @Override
    public boolean saveOrUpdate(SelfExamination entity) {
        return super.saveOrUpdate(entity);
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
