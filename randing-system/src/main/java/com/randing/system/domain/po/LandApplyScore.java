package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 崖州湾实验室南繁用地申请评分标准
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandApplyScore implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String landApplyUnitScoreAttach;

    /**
     * 用地申报单位评分标注
     */
    @ApiModelProperty("用地申报单位评分标注")
    private String landApplyUnitScore;

    /**
     * 用地申报单位评分标注附件
     */
    @ApiModelProperty("用地申报单位评分标注附件")
    private String researchDirectionsScoreAttach;

    /**
     * 科研方向评分标准
     */
    @ApiModelProperty("科研方向评分标准")
    private String researchDirectionsScore;

    /**
     * 科研方向评分标准附件
     */
    @ApiModelProperty("科研方向评分标准附件")
    private String startResearchActivityScoreAttach;

    /**
     * 开展科研活动评分标准
     */
    @ApiModelProperty("开展科研活动评分标准")
    private String startResearchActivityScore;

    /**
     * 开展科研活动评分标准附件
     */
    @ApiModelProperty("开展科研活动评分标准附件")
    private String landQuitLandRestoreScoreAttach;

    /**
     * 用地退出土地评分标准
     */
    @ApiModelProperty("用地退出土地评分标准")
    private String landQuitLandRestoreScore;

    /**
     * 用地退出土地评分标准附件
     */
    @ApiModelProperty("用地退出土地评分标准附件")
    private String driveLandGrowScoreAttach;

    /**
     * 带动当地发展评分标准
     */
    @ApiModelProperty("带动当地发展评分标准")
    private String driveLandGrowScore;

    /**
     * 带动当地发展评分标准附件
     */
    @ApiModelProperty("带动当地发展评分标准附件")
    private String onLandTransformScoreAttach;

    /**
     * 就地转化评分标准
     */
    @ApiModelProperty("就地转化评分标准")
    private String onLandTransformScore;

    /**
     * 就地转化评分标准附件
     */
    @ApiModelProperty("就地转化评分标准附件")
    private String landFulfillPromiseScoreAttach;

    /**
     * 用地承诺兑现程度评分标准
     */
    @ApiModelProperty("用地承诺兑现程度评分标准")
    private String landFulfillPromiseScore;

    /**
     * 总分
     */
    @ApiModelProperty("总分")
    private Double sumScore;

    /**
     * 申请表id
     */
    @ApiModelProperty("申请表id")
    private Integer applyFormId;

    /**
     * 申请表类型
     */
    @ApiModelProperty("申请表类型")
    private String applyFormType;

    private String recordEachScoreByOrder;

    /**
     * 是否删除 0 使用 1 删除
     */
    @ApiModelProperty("是否删除 0 使用 1 删除")
    private Integer delFlag;

    private Integer createUser;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer updateUser;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 调整分数的理由
     */
    @ApiModelProperty("调整分数的理由")
    private String scoreReason;

    /**
     * 调整的分数
     */
    @ApiModelProperty("调整的分数")
    private Double adjustmentScore;

    /**
     * 申报单位科研机构选项（科研机构、高校、涉农企业）
     */
    @ApiModelProperty("申报单位科研机构选项（科研机构、高校、涉农企业）")
    private String researchSbdw;

    /**
     * 新品种就地转化评分
     */
    @ApiModelProperty("新品种就地转化评分")
    private String newVarietiesScore;

    /**
     * 新技术就地转化评分
     */
    @ApiModelProperty("新技术就地转化评分")
    private String newTechniqueScore;


}
