package com.randing.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step7SelfExamination implements Serializable {

    private static final long serialVersionUID = -9192482559459605751L;

    /**
     * 是否南繁登记（0、否，1、是）
     */
    @ApiModelProperty("是否南繁登记（0、否，1、是）")
    @NotBlank(message = "是否完成南繁登记不能为空")
    private String register;
    /*产地检疫----------------------------*/
    /**
     * 是否申请南繁产地检疫（0、否，1、是）（产地检疫）
     */
    @ApiModelProperty("是否申请南繁产地检疫（0、否，1、是）（产地检疫）")
    @NotBlank(message = "是否申请南繁产地检疫不能为空")
    private String originalAreaQuarantineInspection;

    /**
     * 产地检疫结果（0、否，1、是）（产地检疫）
     */
    @NotBlank(message = "产地检疫结果不能为空")
    @ApiModelProperty("产地检疫结果（0、否，1、是）（产地检疫）")
    private String quarantineResults;

    /*转基因----------------------------*/
    /**
     * 是否开展转基因试验（0、否，1、是）（转基因）
     */
    @NotBlank(message = "是否开展转基因试验不能为空")
    @ApiModelProperty("是否开展转基因试验（0、否，1、是）（转基因）")
    private String transgene;

    /**
     * 是否有农业农村部批文（0、否，1、是）（转基因）
     */
    @NotBlank(message = "是否有农业农村部批文不能为空")
    @ApiModelProperty("是否有农业农村部批文（0、否，1、是）（转基因）")
    private String approval;

    /**
     * 是否南繁备案（0、否，1、是）（转基因）
     */
    @NotBlank(message = "是否南繁备案不能为空")
    @ApiModelProperty("是否南繁备案（0、否，1、是）（转基因）")
    private String transgeneKeepRecord;

    /**
     * 是否生物转基因抽查（0、否，1、是）（转基因）
     */
    @NotBlank(message = "是否生物转基因抽查不能为空")
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

    /*毒原植物----------------------------*/

    /**
     * 是否种植毒原作物（0、否，1、是）（毒原作物）
     */
    @NotBlank(message = "是否种植毒原作物不能为空")
    @ApiModelProperty("是否种植毒原作物（0、否，1、是）（毒原作物）")
    private String toxicCrops;

    /**
     * 是否向公安机关备案（0、否，1、是）（毒原作物）
     */
    @NotBlank(message = "是否向公安机关备案不能为空")
    @ApiModelProperty("是否向公安机关备案（0、否，1、是）（毒原作物）")
    private String publicSecurityOrganFiling;


/*境外企业----------------------------*/

    /**
     * 是否属于境外企业（0、否，1、是）（境外企业）
     */
    @ApiModelProperty("是否属于境外企业（0、否，1、是）（境外企业）")
    @NotBlank(message = "是否属于境外企业不能为空")
    private String abroad;

    /**
     * 是否完成境外引种检疫审批（0、否，1、是）（境外企业）
     */
    @NotBlank(message = "是否完成境外引种检疫审批不能为空")
    @ApiModelProperty("是否完成境外引种检疫审批（0、否，1、是）（境外企业）")
    private String quarantineApproval;

    /**
     * 隔离检疫试种是否合格（0、否，1、是）（境外企业）
     */
    @NotBlank(message = "隔离检疫试种是否合格不能为空")
    @ApiModelProperty("隔离检疫试种是否合格（0、否，1、是）（境外企业）")
    @TableField("Isolation_test_and_quarantine")
    private String isolationTestAndQuarantine;

    /**
     * 是否南繁备案（0、否，1、是）（境外企业）
     */
    @NotBlank(message = "是否南繁备案不能为空")
    @ApiModelProperty("是否南繁备案（0、否，1、是）（境外企业）")
    private String abroadKeepRecode;
}
