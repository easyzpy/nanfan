package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-04-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReturnPeople implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    /**
     * 返回人员id
     */
    @ApiModelProperty("返回人员id")
    private String returnPeopleId;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String peopleName;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String peoplePhone;

    /**
     * 到达日期
     */
    @ApiModelProperty("到达日期")
    private LocalDateTime arrivalDate;

    /**
     * 返回前出发地
     */
    @ApiModelProperty("返回前出发地")
    private String placeDeparture;

    /**
     * 返回途经地
     */
    @ApiModelProperty("返回途经地")
    private String via;

    /**
     * 交通方式
     */
    @ApiModelProperty("交通方式")
    private String modeTransportation;

    /**
     * 有无咳嗽、发热等不适症状（0、无，1、有）
     */
    @ApiModelProperty("有无咳嗽、发热等不适症状（0、无，1、有）")
    private String symptom;

    /**
     * 不适症状说明
     */
    @ApiModelProperty("不适症状说明")
    private String symptomMark;

    /**
     * 三亚居住地址
     */
    @ApiModelProperty("三亚居住地址")
    private String sanyaResidentialAddress;

    /**
     * 单位信息
     */
    @ApiModelProperty("单位信息")
    private String company;

    /**
     * 基地位置
     */
    @ApiModelProperty("基地位置")
    private String address;

    /**
     * 基地联系人
     */
    @ApiModelProperty("基地联系人")
    private String baseContact;

    /**
     * 基地联系人电话
     */
    @ApiModelProperty("基地联系人电话")
    private String baseContactPhone;

    /**
     * 是否已接种第三针疫苗
     */
    @ApiModelProperty("是否已接种第三针疫苗")
    private String vaccines;

    /**
     * 未接种第三针疫苗说明
     */
    @ApiModelProperty("未接种第三针疫苗说明")
    private String vaccinesMark;

    /**
     * 是否做过核酸检测（0、否，1、是）
     */
    @ApiModelProperty("是否做过核酸检测（0、否，1、是）")
    private String nucleicAcidTest;

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

    /**
     * 南繁单位自查信息表id
     */
    @ApiModelProperty("南繁单位自查信息表id")
    private String selfExaminationId;

    /**
     * 学历
     */
    @ApiModelProperty("学历")
    private String education;

    /**
     * 主要科研工作内容
     */
    @ApiModelProperty("主要科研工作内容")
    private String researchContent;


}
