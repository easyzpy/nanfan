package com.randing.system.domain.pdf;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.randing.common.annotation.Excel;
import com.randing.common.annotation.Pdf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author patrick
 * @Description 受试者导出Pdf
 * @Date 2021/8/13
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SubjectFilterPdf {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private Long prId;

    /**
     * 项目编号
     */
    private String prNo;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 研究中心id
     */
    private Long siteId;

    /**
     * 研究中心编号
     */
    private String siteNo;

    /**
     * 研究中心名字
     */
    private String siteName;

    /**
     * 中心实验室id
     */
    private Long labId;

    /**
     * 中心实验室id
     */
    private String labName;

    /**
     * 预筛选编号
     */
    private String preSubjectNo;

    /**
     * 受试者编号
     */
    @Pdf(name = "受试者筛选号")
    private String subjectNo;

    /**
     * 姓名缩写
     */
    @Pdf(name = "姓名缩写")
    private String nameAbbr;

    /**
     * 出生日期(年，年月或年月日)
     */
    @Pdf(name = "出生日期")
    private String birthday;

    /**
     * 年龄
     */
    @Pdf(name = "年龄")
    private Integer age;

    /**
     * 性别（0：男；1：女）
     */
    @Pdf(name = "性别", readConverterExp = "0=男,1=女")
    private Integer sex;

    /**
     * 是否停用:1-是，0-否
     */
    @Pdf(name = "状态", readConverterExp = "0=活动,1=停用")
    private Integer isDeactive;

    /**
     * 停用原因
     */
    private String reasonDeactive;

    /**
     * 是否填写检测结果：0-未填，1-已填
     */
    @Pdf(name = "是否填写结果", readConverterExp = "0=未填写结果,1=已填写结果")
    private Integer isFillDetectRes;

    /**
     * 受试者状态:  1-预筛选中, 2-预筛选成功, 3 -预筛选失败, 4-筛选中, 5-筛选成功，6-筛选失败，7-入组成功, 8-退出治疗，9-退出研究
     */
    @Pdf(name = "受试者状态", readConverterExp = "1=预筛选中,2=预筛选成功,3=预筛选失败,4=筛选中,5=筛选成功,6=筛选失败,7=治疗中,8=退出治疗,9=退出研究")
    private Integer status;

    /**
     * 筛选成功日期
     */
    private LocalDate screenSuccessDate;

    /**
     * 筛选失败日期
     */
    @Pdf(name = "筛选失败日期")
    private LocalDate screenFailDate;

    /**
     * 筛选失败原因
     */
    @Pdf(name = "筛选失败原因")
    private String screenFailReason;

    /**
     * 筛选失败说明
     */
    @Pdf(name = "筛选失败说明")
    private String screenFailExplanation;

    /**
     * 签署知情同意书时间
     */
    @Pdf(name = "签署知情同意书时间")
    private LocalDate screenAgreeDate;

    /**
     * 国家名称
     */
    @Pdf(name = "国家/地区")
    private String countryName;

    /**
     * 队列
     */
    private Long cohortId;

    /**
     * 队列
     */
    @Pdf(name = "队列")
    private String cohortName;

    /**
     * 修改队列原因
     */
    private String updateCohortReason;

    /**
     * 分组选择方法
     */
    private String armSelectionMethod;

    /**
     * 分层因素
     */
    private String factor;

    /**
     * 随机时间
     */
    private LocalDateTime randomTime;

    /**
     * 分组
     */
    private String assignedArm;

    /**
     * 分组
     */
    private String armName;

    /**
     * 受试者滴定水平
     */
    private Double titrationLevel;

    /**
     * 剂量因素
     */
    private String doseFactor;

    /**
     * 首次发药时间
     */
    private LocalDateTime firstVisitDate;

    /**
     * 受试者本周期首次滴定用药量
     */
    private Double firstTitration;

    /**
     * 最后一次访视时间
     */
    private String lastVisitName;

    /**
     * 访视顺延天数
     */
    private Integer visitDays;

    /**
     * 首次揭盲人
     */
    private String firstUnblindUser;

    /**
     * 首次揭盲时间
     */
    private LocalDateTime firstUnblindTime;

    /**
     * 首次揭盲原因
     */
    private String firstUnblindReason;

    /**
     * 退出治疗原因
     */
    private String exitTherapyReason;

    /**
     * 退出治疗日期
     */
    private LocalDate eixtTherapyDate;

    /**
     * 退出研究原因
     */
    private String exitPrReason;

    /**
     * 退出研究日期
     */
    private LocalDate exitPrDate;

    /**
     * 筛选标识
     */
    private String screenIdentity;

    /**
     * 逻辑删除标记  0-未删除 1-已删除
     */
    private Integer delFlag;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 开放药
     */
    private String openDrugs;

    /**
     * 随机id
     */
    private String randomId;
}
