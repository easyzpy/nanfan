package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandContract implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 合同id
     */
    @ApiModelProperty("合同id")
    private String contractId;

    /**
     * 用地申请id
     */
    @ApiModelProperty("用地申请id")
    private Integer applyId;

    /**
     * 合同签订时所选择的地块id
     */
    @ApiModelProperty("合同签订时所选择的地块id")
    private Integer choiceLandId;

    /**
     * 甲方单位名称
     */
    @ApiModelProperty("甲方单位名称")
    private String nailName;

    /**
     * 甲方联系人
     */
    @ApiModelProperty("甲方联系人")
    private String nailContacts;

    /**
     * 甲方联系电话
     */
    @ApiModelProperty("甲方联系电话")
    private String nailPhone;

    /**
     * 乙方单位名称
     */
    @ApiModelProperty("乙方单位名称")
    private String secondName;

    /**
     * 乙方联系人
     */
    @ApiModelProperty("乙方联系人")
    private String secondContacts;

    /**
     * 乙方联系电话
     */
    @ApiModelProperty("乙方联系电话")
    private String secondPhone;

    /**
     * 合同签订时所使用的地块面积
     */
    @ApiModelProperty("合同签订时所使用的地块面积")
    private Double landArea;

    /**
     * 合同的开始时间
     */
    @ApiModelProperty("合同的开始时间")
    private LocalDateTime contractStartTime;

    /**
     * 合同的结束时间
     */
    @ApiModelProperty("合同的结束时间")
    private LocalDateTime contractEndTime;

    /**
     * 上传的合同文件路径
     */
    @ApiModelProperty("上传的合同文件路径")
    private String contractUrl;

    /**
     * 合同的状态（1：已生效，2：已结束）
     */
    @ApiModelProperty("合同的状态（1：已生效，2：已结束）")
    private String contractStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime addTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String addUser;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateUser;

    /**
     * 使用地块的状态（1：使用中，2：退地中，3：已退地）
     */
    @ApiModelProperty("使用地块的状态（1：使用中，2：退地中，3：已退地）")
    private String landStatus;

    /**
     * 合同的所属单位（发起地块申请的用户id）
     */
    @ApiModelProperty("合同的所属单位（发起地块申请的用户id）")
    private Integer applyUserId;

    private String signType;

    private String offlineContractUrl;


}
