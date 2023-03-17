package com.randing.system.service;

import com.randing.system.domain.po.SelfExamination;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
public interface ISelfExaminationService extends IService<SelfExamination> {

    int step1save(SelfStep1ReqVo reqVo);

    int step2Permanent(Step2SelfExaminationPermanentReqVo reqVo);

    List<SelfExaminationPermanent> step2GetPermanentList();

    SelfExamination getSelfExamination();

    List<SelfExaminationBase> step3GetBasetList();

    int step3Base(Step3SelfExaminationBaseReqVo reqVo);

    int step4Activity(Step4SelfExaminationActivityReqVo reqVo);

    List<SelfExaminationActivity> step4GetActivityList();


    int step5Gain(Step5SelfExaminationGainReqVo reqVo);

    List<SelfExaminationGain> step5GetGainList();
}
