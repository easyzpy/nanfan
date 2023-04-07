package com.randing.system.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-04-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceTaskNotesMoeny implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 序号，确保数据的有序性（服务跟踪费用管理）
     */
    @ApiModelProperty("")
    private Integer orders;

    /**
     * 订单费用
     */
    @ApiModelProperty("订单费用")
    private String orderMoney;

    /**
     * 支付方式
     */
    @ApiModelProperty("支付方式")
    private String moneyType;

    /**
     * 订单状态
     */
    @ApiModelProperty("订单状态")
    private String orderStatus;

    /**
     * 退款状态
     */
    @ApiModelProperty("退款状态")
    private String returnMoneyStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty("")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty("")
    private Integer createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty("")
    private Integer updateBy;

    /**
     * 费用跟踪id
     */
    @ApiModelProperty("费用跟踪id")
    private String serviceTakeNotesId;


}
