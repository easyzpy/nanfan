package com.randing.system.service;

import com.randing.system.domain.po.ReturnPeople;
import com.randing.system.domain.po.SelfExamination;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationExtension;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationNewCategory;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step10ReturnPeople;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;
import com.randing.system.domain.vo.Step6SelfExamination;
import com.randing.system.domain.vo.Step7SelfExamination;
import com.randing.system.domain.vo.Step8SelfExamination;
import com.randing.system.domain.vo.Step8SelfExaminationExtension;
import com.randing.system.domain.vo.Step8SelfExaminationNewCategory;
import com.randing.system.domain.vo.Step9SelfExamination;

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

    int step6Emp(Step6SelfExamination reqVo);

    int step7Secure(Step7SelfExamination reqVo);

    List<SelfExaminationNewCategory> step8CategoryList();

    List<SelfExaminationExtension> step8ExtensionList();

    int step8Science(Step8SelfExamination reqVo);

    void step8Category(Step8SelfExaminationNewCategory reqVo);

    void step8Extension(Step8SelfExaminationExtension reqVo);

    int step9Remark(Step9SelfExamination reqVo);

    SelfExamination findMyExamination();

    void step2DeletePermanent(Long id);

    void step8DeleteCategory(Long id);

    void step8DeleteExtension(Long id);

    void step10ReturnPeople(Step10ReturnPeople reqVo);

    List<ReturnPeople> step10RemarkList();

    void step10DeleteReturnPeople(Long id);
}
