package com.randing.system.domain.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 信息自查表
 * </p>
 *
 * @author Leen
 * @since 2023-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExamination implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;
    /**
     * 南翻单位自查信息统计表id
     */
    @ApiModelProperty("南翻单位自查信息统计表id")
    private String selfExaminationId;

    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String companyName;

    /**
     * 基地联系人
     */
    @ApiModelProperty("基地联系人")
    private String baseContact;

    /**
     * 联系人手机（联系方式）
     */
    @ApiModelProperty("联系人手机（联系方式）")
    private String contactPhone;

    /**
     * 微信（联系方式）
     */
    @ApiModelProperty("微信（联系方式）")
    private String contactWeixing;

    /**
     * 基地位置（具体到村小组）
     */
    @ApiModelProperty("基地位置（具体到村小组）")
    private String baseAddress;

    /**
     * 管理人数（南繁科研人员人数）
     */
    @ApiModelProperty("管理人数（南繁科研人员人数）")
    private Integer administratorNumber;

    /**
     * 科研人数（南繁科研人员人数）
     */
    @ApiModelProperty("科研人数（南繁科研人员人数）")
    private Integer scientificResearchNumber;

    /**
     * 工人人数（南繁科研人员人数）
     */
    @ApiModelProperty("工人人数（南繁科研人员人数）")
    private Integer workerNumber;

    /**
     * 是否南繁登记（0、否，1、是）
     */
    @ApiModelProperty("是否南繁登记（0、否，1、是）")
    private String register;

    /**
     * 列入南繁核心区面积（亩）
     */
    @ApiModelProperty("列入南繁核心区面积（亩）")
    private Double coreArea;

    /**
     * 是否建有建筑设施（0、否，1、是）（基础设施）
     */
    @ApiModelProperty("是否建有建筑设施（0、否，1、是）（基础设施）")
    private String constructionFacilities;

    /**
     * 建筑设施是否有合法手续（0、否，1、是）（基础设施）
     */
    @ApiModelProperty("建筑设施是否有合法手续（0、否，1、是）（基础设施）")
    private String keepRecord;

    /**
     * 建筑设施面积
     */
    @ApiModelProperty("建筑设施面积")
    private Double constructionFacilitiesAres;

    /**
     * 单位性质（1、高校，2、科研院所，3、企业，4、其他）
     */
    @ApiModelProperty("单位性质（1、高校，2、科研院所，3、企业，4、其他）")
    private String unitNature;

    /**
     * 土地流转来源（x村小组/x公司）（土地租赁年限及租金）
     */
    @ApiModelProperty("土地流转来源（x村小组/x公司）（土地租赁年限及租金）")
    private String circulationSource;

    /**
     * 土地流转开始年限（土地租赁年限及租金）
     */
    @ApiModelProperty("土地流转开始年限（土地租赁年限及租金）")
    private LocalDateTime circulationSourceYear;

    /**
     * 土地流转结束年限（土地租赁年限及租金）
     */
    @ApiModelProperty("土地流转结束年限（土地租赁年限及租金）")
    private LocalDateTime circulationSourceEndYear;

    /**
     * 地租（元/亩/年）（土地租赁年限及租金）
     */
    @ApiModelProperty("地租（元/亩/年）（土地租赁年限及租金）")
    private Double landRent;

    /**
     * 何时入驻崖州区（年份）（土地租赁年限及租金）
     */
    @ApiModelProperty("何时入驻崖州区（年份）（土地租赁年限及租金）")
    private String checkIn;

    /**
     * 使用类型（0、常年，1、当年）（土地租赁年限及租金）
     */
    @ApiModelProperty("使用类型（0、常年，1、当年）（土地租赁年限及租金）")
    private String useType;
    /*用工需求--------------------------*/
    /**
     * 普通工人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("普通工人数（南翻服务需求—用工需求）")
    private Integer ordinaryWorker;

    /**
     * 技术工人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("技术工人数（南翻服务需求—用工需求）")
    private Integer technicalWorker;

    /**
     * 其他人数（南翻服务需求—用工需求）
     */
    @ApiModelProperty("其他人数（南翻服务需求—用工需求）")
    private Integer otherWorker;
    /*用地需求---------------------*/
    /**
     * 试验用地面积（南繁服务需求—用地需求）
     */
    @ApiModelProperty("试验用地面积（南繁服务需求—用地需求）")
    private Double testLand;

    /**
     * 实施用地面积（南繁服务需求—用地需求）
     */
    @ApiModelProperty("实施用地面积（南繁服务需求—用地需求）")
    private Double implementationLand;
    /*服务需求------------------------*/
    /**
     * 农事服务需求（0、无，1、有）（南繁服务需求）
     */
    @ApiModelProperty("农事服务需求（0、无，1、有）（南繁服务需求）")
    private String serviceDemand;

    /**
     * 具体农事服务需求（南繁服务需求）
     */
    @ApiModelProperty("具体农事服务需求（南繁服务需求）")
    private String serviceDemandDescribe;

    /**
     * 是否有委托育种、制种需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有委托育种、制种需求（0、没有，1、有）（南繁服务需求）")
    private String entrustedBreeding;

    /**
     * 具体委托育种、制种需求（南繁服务需求）
     */
    @ApiModelProperty("具体委托育种、制种需求（南繁服务需求）")
    private String entrustedBreedingDescribe;

    /**
     * 是否有实验室需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有实验室需求（0、没有，1、有）（南繁服务需求）")
    private String laboratoryRequirements;

    /**
     * 具体实验室需求（南繁服务需求）
     */
    @ApiModelProperty("具体实验室需求（南繁服务需求）")
    private String laboratoryRequirementsDescribe;

    /**
     * 是否有其他需求（0、没有，1、有）（南繁服务需求）
     */
    @ApiModelProperty("是否有其他需求（0、没有，1、有）（南繁服务需求）")
    private String otherRequirements;

    /**
     * 具体其他需求（南繁服务需求）
     */
    @ApiModelProperty("具体其他需求（南繁服务需求）")
    private String otherRequirementsDescribe;
    /*------------*/
    /**
     * 是否种植毒原作物（0、否，1、是）（毒原作物）
     */
    @ApiModelProperty("是否种植毒原作物（0、否，1、是）（毒原作物）")
    private String toxicCrops;

    /**
     * 是否向公安机关备案（0、否，1、是）（毒原作物）
     */
    @ApiModelProperty("是否向公安机关备案（0、否，1、是）（毒原作物）")
    private String publicSecurityOrganFiling;

    /**
     * 是否开展转基因试验（0、否，1、是）（转基因）
     */
    @ApiModelProperty("是否开展转基因试验（0、否，1、是）（转基因）")
    private String transgene;

    /**
     * 是否有农业农村部批文（0、否，1、是）（转基因）
     */
    @ApiModelProperty("是否有农业农村部批文（0、否，1、是）（转基因）")
    private String approval;

    /**
     * 是否南繁备案（0、否，1、是）（转基因）
     */
    @ApiModelProperty("是否南繁备案（0、否，1、是）（转基因）")
    private String transgeneKeepRecord;

    /**
     * 是否生物转基因抽查（0、否，1、是）（转基因）
     */
    @ApiModelProperty("是否生物转基因抽查（0、否，1、是）（转基因）")
    private String transgeneSpotcheck;

    /**
     * 转基因抽查时间（转基因）
     */
    @ApiModelProperty("转基因抽查时间（转基因）")
    private LocalDateTime transgeneTime;

    /**
     * 抽查作物（转基因）
     */
    @ApiModelProperty("抽查作物（转基因）")
    private String transgeneCrop;

    /**
     * 抽查的基地位置（转基因）
     */
    @ApiModelProperty("抽查的基地位置（转基因）")
    private String transgeneAddress;

    /**
     * 基地位置的经度（转基因）
     */
    @ApiModelProperty("基地位置的经度（转基因）")
    private Double transgeneThrough;

    /**
     * 基地位置的纬度（转基因）
     */
    @ApiModelProperty("基地位置的纬度（转基因）")
    private Double transgeneLatitude;

    /**
     * 抽查的单位（转基因）
     */
    @ApiModelProperty("抽查的单位（转基因）")
    private String transgeneCompany;

    /**
     * 是否属于境外企业（0、否，1、是）（境外企业）
     */
    @ApiModelProperty("是否属于境外企业（0、否，1、是）（境外企业）")
    private String abroad;

    /**
     * 是否完成境外引种检疫审批（0、否，1、是）（境外企业）
     */
    @ApiModelProperty("是否完成境外引种检疫审批（0、否，1、是）（境外企业）")
    private String quarantineApproval;

    /**
     * 隔离检疫试种是否合格（0、否，1、是）（境外企业）
     */
    @ApiModelProperty("隔离检疫试种是否合格（0、否，1、是）（境外企业）")
    @TableField("Isolation_test_and_quarantine")
    private String isolationTestAndQuarantine;

    /**
     * 是否南繁备案（0、否，1、是）（境外企业）
     */
    @ApiModelProperty("是否南繁备案（0、否，1、是）（境外企业）")
    private String abroadKeepRecode;

    /**
     * 是否申请南繁产地检疫（0、否，1、是）（产地检疫）
     */
    @ApiModelProperty("是否申请南繁产地检疫（0、否，1、是）（产地检疫）")
    private String originalAreaQuarantineInspection;

    /**
     * 产地检疫结果（0、否，1、是）（产地检疫）
     */
    @ApiModelProperty("产地检疫结果（0、否，1、是）（产地检疫）")
    private String quarantineResults;

    /**
     * 推广个数（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("推广个数（新品种在崖州区成果转化个数）")
    private Integer promotionNumber;

    /**
     * 品种名称（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("品种名称（新品种在崖州区成果转化个数）")
    private String promotionName;

    /**
     * 累计推广面积（亩）（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("累计推广面积（亩）（新品种在崖州区成果转化个数）")
    private Double promotionArea;

    /**
     * 累计效益（万元）（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("累计效益（万元）（新品种在崖州区成果转化个数）")
    private Double promotionMoney;

    /**
     * 推广个数（新品种在全国成果转化个数）
     */
    @ApiModelProperty("推广个数（新品种在全国成果转化个数）")
    private Integer countryPromotionNumber;

    /**
     * 品种名称（新品种在全国成果转化个数）
     */
    @ApiModelProperty("品种名称（新品种在全国成果转化个数）")
    private String countryPromotionName;

    /**
     * 累计推广面积（亩）（新品种在全国成果转化个数）
     */
    @ApiModelProperty("累计推广面积（亩）（新品种在全国成果转化个数）")
    private Double countryPromotionArea;

    /**
     * 累计效益（万元）（新品种在全国成果转化个数）
     */
    @ApiModelProperty("累计效益（万元）（新品种在全国成果转化个数）")
    private Double countryPromotionMoney;

    /**
     * 科研投入（万元）
     */
    @ApiModelProperty("科研投入（万元）")
    private Double scientificResearchInvestment;

    /**
     * 南繁服务建议
     */
    @ApiModelProperty("南繁服务建议")
    private String serviceSuggestions;

    /**
     * 南繁基层管理服务员联系方式
     */
    @ApiModelProperty("南繁基层管理服务员联系方式")
    private String adminPhone;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 添加用户id
     */
    @ApiModelProperty("添加用户id")
    private String addPeople;

    /**
     * 最后一次的修改时间
     */
    @ApiModelProperty("最后一次的修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 最后一次的修改人
     */
    @ApiModelProperty("最后一次的修改人")
    private String updatePeople;

    /**
     * 审核状态（null、未审核，0、审核失败,1、审核成功）
     */
    @ApiModelProperty("审核状态（null、未审核，0、审核失败,1、审核成功）")
    private String auditStatus;

    /**
     * 审核失败的原因
     */
    @ApiModelProperty("审核失败的原因")
    private String auditStatusReason;
    @ApiModelProperty("社会信用代码")
    private String creditCode;

    @ApiModelProperty("注册地址")
    private String registerAddress;


}
