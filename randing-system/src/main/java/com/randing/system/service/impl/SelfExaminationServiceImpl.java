package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.bean.BeanUtils;
import com.randing.common.utils.iface.dto.YzbUserInfo;
import com.randing.system.domain.po.ReturnPeople;
import com.randing.system.domain.po.SelfBaseFileRelation;
import com.randing.system.domain.po.SelfExamFile;
import com.randing.system.domain.po.SelfExamination;
import com.randing.system.domain.po.SelfExaminationActivity;
import com.randing.system.domain.po.SelfExaminationBase;
import com.randing.system.domain.po.SelfExaminationExtension;
import com.randing.system.domain.po.SelfExaminationGain;
import com.randing.system.domain.po.SelfExaminationGainCheck;
import com.randing.system.domain.po.SelfExaminationNewCategory;
import com.randing.system.domain.po.SelfExaminationPermanent;
import com.randing.system.domain.vo.SelfStep1ReqVo;
import com.randing.system.domain.vo.Step10ReturnPeople;
import com.randing.system.domain.vo.Step2SelfExaminationPermanentReqVo;
import com.randing.system.domain.vo.Step3SelfExaminationBaseReqVo;
import com.randing.system.domain.vo.Step4SelfExaminationActivityReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainCheckReqVo;
import com.randing.system.domain.vo.Step5SelfExaminationGainReqVo;
import com.randing.system.domain.vo.Step6SelfExamination;
import com.randing.system.domain.vo.Step7SelfExamination;
import com.randing.system.domain.vo.Step8SelfExamination;
import com.randing.system.domain.vo.Step8SelfExaminationExtension;
import com.randing.system.domain.vo.Step8SelfExaminationNewCategory;
import com.randing.system.domain.vo.Step9SelfExamination;
import com.randing.system.mapper.SelfExaminationMapper;
import com.randing.system.service.IReturnPeopleService;
import com.randing.system.service.ISelfBaseFileRelationService;
import com.randing.system.service.ISelfExamFileService;
import com.randing.system.service.ISelfExaminationActivityService;
import com.randing.system.service.ISelfExaminationBaseService;
import com.randing.system.service.ISelfExaminationExtensionService;
import com.randing.system.service.ISelfExaminationGainCheckService;
import com.randing.system.service.ISelfExaminationGainService;
import com.randing.system.service.ISelfExaminationNewCategoryService;
import com.randing.system.service.ISelfExaminationPermanentService;
import com.randing.system.service.ISelfExaminationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    @Autowired
    private ISelfExaminationNewCategoryService selfExaminationNewCategoryService;
    @Autowired
    private ISelfExaminationExtensionService selfExaminationExtensionService;
    @Autowired
    private ISelfExamFileService fileService;
    @Autowired
    private ISelfBaseFileRelationService selfBaseFileRelationService;
    @Autowired
    private IReturnPeopleService returnPeopleService;
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
        selfExaminationPermanentService.saveOrUpdate(selfExaminationPermanent);
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
//        selfExaminationBase.setBaseId(getUUID());
        selfExaminationBase.setSelfExaminationId(selfExamination.getSelfExaminationId());
        List<SelfExaminationBase> list = selfExaminationBaseService.list(Wrappers.lambdaQuery(SelfExaminationBase.class)
                .eq(SelfExaminationBase::getSelfExaminationId, selfExamination.getSelfExaminationId())
                .eq(SelfExaminationBase::getBaseId, reqVo.getBaseID())
        );
        if (!list.isEmpty()) {
            Long id = list.get(0).getId();
            selfExaminationBase.setId(id);
        } else {
            selfExaminationBase.setBaseId(getUUID());
        }
        selfExaminationBaseService.saveOrUpdate(selfExaminationBase);
        //保存基地附件信息
        if (!CollectionUtils.isEmpty(reqVo.getFileIds())) {
            int count = fileService.count(Wrappers.lambdaQuery(SelfExamFile.class).in(SelfExamFile::getFileId, reqVo.getFileIds()));
            if (count != reqVo.getFileIds().size()) {
                throw new BaseException("图片数量上传异常");
            }
            selfBaseFileRelationService.remove(Wrappers.lambdaQuery(SelfBaseFileRelation.class).eq(SelfBaseFileRelation::getBaseId, selfExaminationBase.getBaseId()));
            ArrayList<SelfBaseFileRelation> selfBaseFileRelations = new ArrayList<>();
            for (String fileId : reqVo.getFileIds()) {
                SelfBaseFileRelation selfBaseFileRelation = new SelfBaseFileRelation();
                selfBaseFileRelation.setBaseId(selfExaminationBase.getBaseId());
                selfBaseFileRelation.setFileId(fileId);
                selfBaseFileRelations.add(selfBaseFileRelation);
            }

            selfBaseFileRelationService.saveBatch(selfBaseFileRelations);
        }

        return 0;
    }

    @Override
    public List<SelfExaminationBase> step3GetBasetList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationBase> list = selfExaminationBaseService.list(Wrappers.lambdaQuery(SelfExaminationBase.class).eq(SelfExaminationBase::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        //查询所有基地附件
        if (!CollectionUtils.isEmpty(list)) {
            List<SelfBaseFileRelation> fileRelationList = selfBaseFileRelationService.list(Wrappers.lambdaQuery(SelfBaseFileRelation.class).in(SelfBaseFileRelation::getBaseId, list.stream().map(SelfExaminationBase::getBaseId).collect(Collectors.toList())));
            Map<String, List<SelfBaseFileRelation>> collect = fileRelationList.stream().collect(Collectors.groupingBy(SelfBaseFileRelation::getBaseId));
            List<String> fileIds = fileRelationList.stream().map(SelfBaseFileRelation::getFileId).collect(Collectors.toList());
//            List<SelfExamFile> selfExamFiles = fileService.listByIds(fileIds);
            List<SelfExamFile> selfExamFiles = fileService.list(Wrappers.lambdaQuery(SelfExamFile.class).in(SelfExamFile::getFileId, fileIds));
            Map<String, SelfExamFile> fileMap = selfExamFiles.stream().collect(Collectors.toMap(SelfExamFile::getFileId, v->v));

            for (SelfExaminationBase base : list) {
                List<SelfBaseFileRelation> fileRelationList1 = collect.get(base.getBaseId());
                if (!CollectionUtils.isEmpty(fileRelationList1)) {
                    ArrayList<SelfExamFile> selfExamFileArrayList = new ArrayList<>();
                    for (SelfBaseFileRelation selfBaseFileRelation : fileRelationList1) {
                        SelfExamFile selfExamFile = fileMap.get(selfBaseFileRelation.getFileId());
                        if (selfExamFile != null) {
                            selfExamFileArrayList.add(selfExamFile);
                        }
                    }
                    base.setSelfExamFileList(selfExamFileArrayList);
                }
            }

        }
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
        List<SelfExaminationActivity> list = selfExaminationActivityService.list(Wrappers.lambdaQuery(SelfExaminationActivity.class).eq(SelfExaminationActivity::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        if (!list.isEmpty()) {
            Long id = list.get(0).getId();
            selfExaminationActivity.setId(id);
        }
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
        List<SelfExaminationGain> list = selfExaminationGainService.list(Wrappers.lambdaQuery(SelfExaminationGain.class)
                .eq(SelfExaminationGain::getGainId, selfExaminationGain.getGainId())
                .eq(SelfExaminationGain::getSelfExaminationId, selfExamination.getSelfExaminationId()).last(" limit 1"));
        SelfExaminationGain selfExaminationGain1 = null;
        if (CollectionUtils.isEmpty(list)) {
            selfExaminationGainService.save(selfExaminationGain);
            list = selfExaminationGainService.list(Wrappers.lambdaQuery(SelfExaminationGain.class)
                    .eq(SelfExaminationGain::getGainId, selfExaminationGain.getGainId())
                    .eq(SelfExaminationGain::getSelfExaminationId, selfExamination.getSelfExaminationId()).last(" limit 1"));
            selfExaminationGain1 = list.get(0);
        }else {
            selfExaminationGain.setId(list.get(0).getId());
            selfExaminationGainService.updateById(selfExaminationGain);
            selfExaminationGain1 = selfExaminationGain;
        }


        if (CollectionUtils.isEmpty(reqVo.getGainCheck())) {
            throw new BaseException("锁定品种信息不能为空");
        }
        for (Step5SelfExaminationGainCheckReqVo step5SelfExaminationGainCheckReqVo : reqVo.getGainCheck()) {
            //保存check
            SelfExaminationGainCheck selfExaminationGainCheck = new SelfExaminationGainCheck();
            BeanUtils.copyProperties(step5SelfExaminationGainCheckReqVo, selfExaminationGainCheck);
            selfExaminationGainCheck.setGainId(selfExaminationGain1.getGainId());
            selfExaminationGainCheck.setCheckId(getUUID());
            selfExaminationGainCheckService.save(selfExaminationGainCheck);
        }

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

    @Override
    public int step6Emp(Step6SelfExamination reqVo) {
        SelfExamination selfExamination = new SelfExamination();
        BeanUtils.copyProperties(reqVo, selfExamination);
        SelfExamination dbSelfExamination = this.getSelfExamination();
        if (dbSelfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        selfExamination.setId(dbSelfExamination.getId());
        updateById(selfExamination);
        return 0;
    }

    @Override
    public int step7Secure(Step7SelfExamination reqVo) {
        SelfExamination selfExamination = new SelfExamination();
        BeanUtils.copyProperties(reqVo, selfExamination);
        SelfExamination dbSelfExamination = this.getSelfExamination();
        if (dbSelfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        selfExamination.setId(dbSelfExamination.getId());
        updateById(selfExamination);
        return 0;
    }

    @Override
    public List<SelfExaminationNewCategory> step8CategoryList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationNewCategory> list = selfExaminationNewCategoryService.list(Wrappers.lambdaQuery(SelfExaminationNewCategory.class).eq(SelfExaminationNewCategory::getSelfExaminationId, selfExamination.getSelfExaminationId()));

        return list;
    }

    @Override
    public List<SelfExaminationExtension> step8ExtensionList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<SelfExaminationExtension> list = selfExaminationExtensionService.list(Wrappers.lambdaQuery(SelfExaminationExtension.class).eq(SelfExaminationExtension::getSelfExaminationId, selfExamination.getSelfExaminationId()));

        return list;
    }

    @Override
    public int step8Science(Step8SelfExamination reqVo) {
        SelfExamination selfExamination = new SelfExamination();
        BeanUtils.copyProperties(reqVo, selfExamination);
        SelfExamination dbSelfExamination = this.getSelfExamination();
        if (dbSelfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        selfExamination.setId(dbSelfExamination.getId());
        updateById(selfExamination);
        return 0;
    }

    @Override
    public void step8Category(Step8SelfExaminationNewCategory reqVo) {
        SelfExamination selfExamination = this.getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        SelfExaminationNewCategory selfExaminationNewCategory = new SelfExaminationNewCategory();
        BeanUtils.copyProperties(reqVo, selfExaminationNewCategory);
        selfExaminationNewCategory.setNewCategoryId(getUUID());
        selfExaminationNewCategory.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationNewCategoryService.save(selfExaminationNewCategory);
    }

    @Override
    public void step8Extension(Step8SelfExaminationExtension reqVo) {
        SelfExamination selfExamination = this.getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        SelfExaminationExtension selfExaminationExtension = new SelfExaminationExtension();
        BeanUtils.copyProperties(reqVo, selfExaminationExtension);
        selfExaminationExtension.setExtensionId(getUUID());
        selfExaminationExtension.setSelfExaminationId(selfExamination.getSelfExaminationId());
        selfExaminationExtensionService.save(selfExaminationExtension);
    }

    @Override
    public int step9Remark(Step9SelfExamination reqVo) {
        SelfExamination selfExamination = new SelfExamination();
        BeanUtils.copyProperties(reqVo, selfExamination);
        SelfExamination dbSelfExamination = this.getSelfExamination();
        if (dbSelfExamination == null) {
            throw new BaseException("请先保存基本信息");
        }
        selfExamination.setId(dbSelfExamination.getId());
        updateById(selfExamination);
        return 0;
    }

    @Override
    public SelfExamination findMyExamination() {
        return this.getSelfExamination();
//        return baseMapper.selectById()
    }

    @Override
    public void step2DeletePermanent(Long id) {
//        SelfExamination selfExamination = getSelfExamination();
        selfExaminationPermanentService.remove(Wrappers.lambdaQuery(SelfExaminationPermanent.class).eq(SelfExaminationPermanent::getId, id));
    }

    @Override
    @Transactional
    public void step8DeleteCategory(Long id) {
        boolean b = selfExaminationNewCategoryService.removeById(id);
    }

    @Override
    @Transactional
    public void step8DeleteExtension(Long id) {
        selfExaminationExtensionService.removeById(id);
    }

    @Override
    public void step10ReturnPeople(Step10ReturnPeople reqVo) {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            throw new BaseException("清先保存基本信息");
        }
        ReturnPeople returnPeople = new ReturnPeople();
        BeanUtils.copyProperties(reqVo, returnPeople);
        returnPeople.setReturnPeopleId(getUUID());
        returnPeople.setSelfExaminationId(selfExamination.getSelfExaminationId());
        List<ReturnPeople> list = returnPeopleService.list(Wrappers.lambdaQuery(ReturnPeople.class)
                .eq(ReturnPeople::getSelfExaminationId, selfExamination.getSelfExaminationId()));
        if (!list.isEmpty()) {
            Long id = list.get(0).getId();
            returnPeople.setId(id);
        }

        returnPeopleService.saveOrUpdate(returnPeople);

    }
    @Override
    public void step10DeleteReturnPeople(Long id) {
        returnPeopleService.removeById(id);
    }
    @Override
    public List<ReturnPeople> step10RemarkList() {
        SelfExamination selfExamination = getSelfExamination();
        if (selfExamination == null) {
            return null;
        }
        List<ReturnPeople> list = returnPeopleService.list(Wrappers.lambdaQuery(ReturnPeople.class)
                .eq(ReturnPeople::getSelfExaminationId, selfExamination.getSelfExaminationId())
                .orderByDesc(ReturnPeople::getAddTime)
        );

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
