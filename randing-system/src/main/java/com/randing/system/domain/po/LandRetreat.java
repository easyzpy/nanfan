package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.aspectj.lang.annotation.Around;

/**
 * <p>
 * 退地
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandRetreat implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 退地申请表id
     */
    @ApiModelProperty("退地申请表id")
    private String retreatId;

    /**
     * 申请单位
     */
    @ApiModelProperty("申请单位")
    private String retreatApplicant;

    /**
     * 退地时间
     */
    @ApiModelProperty("退地时间")
    private LocalDateTime retreatTime;

    /**
     * 是否土壤保育（0：否，1：是）
     */
    @ApiModelProperty("是否土壤保育（0：否，1：是）")
    private Integer isConservation;

    /**
     * 有机质含量是否达标（0：否，1：是）
     */
    @ApiModelProperty("有机质含量是否达标（0：否，1：是）")
    private Integer isOrganic;

    /**
     * 是否符合绿色发展（0：否，1：是）
     */
    @ApiModelProperty("是否符合绿色发展（0：否，1：是）")
    private Integer isGreen;

    /**
     * 土地恢复方式（0：土地恢复资金投入占土地租赁投入的20%以上（30分），1：土地恢复资金投入占土地租赁投入的10%以上（20分），2：土地恢复资金投入占土地租赁投入的5%以上（10分），3：土地恢复措施（2分）要提交材料，4：无土地恢复资金投入或措施方案（-10分））
     */
    @ApiModelProperty("土地恢复方式（0：土地恢复资金投入占土地租赁投入的20%以上（30分），1：土地恢复资金投入占土地租赁投入的10%以上（20分），2：土地恢复资金投入占土地租赁投入的5%以上（10分），3：土地恢复措施（2分）要提交材料，4：无土地恢复资金投入或措施方案（-10分））")
    private Integer landRestoration;

    /**
     * 带动当地发展（0：在崖州湾科技城管理局注册的科研机构、公司等（15分），1：在本地完成相关科研成果的认定（15分），2：聘用本地农业技术人员（10分）要附正规劳务合同，3：录用本地农工（5分））
     */
    @ApiModelProperty("带动当地发展（0：在崖州湾科技城管理局注册的科研机构、公司等（15分），1：在本地完成相关科研成果的认定（15分），2：聘用本地农业技术人员（10分）要附正规劳务合同，3：录用本地农工（5分））")
    private Integer driveDevelopment;

    /**
     * 就地转化-新品种就地转化（0：在海南推广种植2000亩以上（15分），1：在海南推广种植500亩以上（10分））
     */
    @ApiModelProperty("就地转化-新品种就地转化（0：在海南推广种植2000亩以上（15分），1：在海南推广种植500亩以上（10分））")
    private Integer situConversion;

    /**
     * 就地转化-新技术就地转化（0：年培训农民200人次以上（15分），1：年培训农民100人次以上（10分））
     */
    @ApiModelProperty("就地转化-新技术就地转化（0：年培训农民200人次以上（15分），1：年培训农民100人次以上（10分））")
    private Integer situConversionJs;

    /**
     * 明年是否续租（0：否，1：是）
     */
    @ApiModelProperty("明年是否续租（0：否，1：是）")
    private Integer isNextYaer;

    /**
     * 系统评分
     */
    @ApiModelProperty("系统评分")
    private Double systemScore;

    /**
     * 调整分数（人工核查）
     */
    @ApiModelProperty("调整分数（人工核查）")
    private Double adjustmentScore;

    /**
     * 最终得分
     */
    @ApiModelProperty("最终得分")
    private Double finalScore;

    /**
     * 申请状态（0：草稿，1：已提交，2：已退回，3：审核通过）
     */
    @ApiModelProperty("申请状态（0：草稿，1：已提交，2：已退回，3：审核通过）")
    private Integer status;

    /**
     * 退回原因
     */
    @ApiModelProperty("退回原因")
    private String returnMarks;

    /**
     * 添加时间
     */
    @ApiModelProperty("添加时间")
    private LocalDateTime addTime;

    /**
     * 添加的用户id
     */
    @ApiModelProperty("添加的用户id")
    private String addUser;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 修改的用户id
     */
    @ApiModelProperty("修改的用户id")
    private String updateUser;

    /**
     * 签订的合同id
     */
    @ApiModelProperty("签订的合同id")
    private String contractId;

    /**
     * 是否已经填写调查满意度调查问卷
     */
    @ApiModelProperty("是否已经填写调查满意度调查问卷")
    private Integer isQuestionnaire;


    @ApiModelProperty("退地附件")
    @TableField(exist = false)
    private List<LandRetreatFile> landRetreatFileList;
}
