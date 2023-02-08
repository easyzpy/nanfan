package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplyObjection implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 异议id
     */
    @ApiModelProperty("异议id")
    private String objcetionId;

    /**
     * 异议原因表
     */
    @ApiModelProperty("异议原因表")
    private String objectionReason;

    /**
     * 发起异议的用户id
     */
    @ApiModelProperty("发起异议的用户id")
    private String userId;

    /**
     * 申请id
     */
    @ApiModelProperty("申请id")
    private Integer applyId;

    /**
     * 异议状态：0 发起异议 1异议通过 2申请答辩
     */
    @ApiModelProperty("异议状态：0 发起异议 1异议通过 2申请答辩")
    private Integer objectionType;

    /**
     * 发起时间
     */
    @ApiModelProperty("发起时间")
    private LocalDateTime addTime;

    /**
     * 最近一次的修改时间
     */
    @ApiModelProperty("最近一次的修改时间")
    private LocalDateTime updateTime;

    /**
     * 最近一次的修改用户id
     */
    @ApiModelProperty("最近一次的修改用户id")
    private String updateUser;

    /**
     * 退回原因
     */
    @ApiModelProperty("退回原因")
    private String returnReason;

    /**
     * 申请的地块id
     */
    @ApiModelProperty("申请的地块id")
    private Integer applyInforId;

    /**
     * 同意之后选择新的地块id
     */
    @ApiModelProperty("同意之后选择新的地块id2")
    private Integer inforId;

    @TableField(exist = false)
    @ApiModelProperty("中标地块")
    private NanfanLandApplyForm formEntity;

    @TableField(exist = false)
    @ApiModelProperty("申请新的地块")
    private LandInfor landInforEntity;


}
