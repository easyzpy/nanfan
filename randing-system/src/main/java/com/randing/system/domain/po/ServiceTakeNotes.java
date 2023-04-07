package com.randing.system.domain.po;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ServiceTakeNotes implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    /**
     * 序号，确保数据在库中的有序性（服务跟踪表）
     */
    @ApiModelProperty("")
    private Integer orders;

    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String goodsName;

    /**
     * 订单编号
     */
    @ApiModelProperty("订单编号")
    private String orderNumber;

    /**
     * 订单类型（农机/用工）
     */
    @ApiModelProperty("订单类型（农机/用工）")
    private String goodsType;

    /**
     * 农机/用工编号
     */
    @ApiModelProperty("农机/用工编号")
    private String machineNumber;

    /**
     * 使用人/用工人
     */
    @ApiModelProperty("使用人/用工人")
    private String user;

    /**
     * 使用人/用工人联系方式
     */
    @ApiModelProperty("使用人/用工人联系方式")
    private String userPhone;

    /**
     * 使用地址
     */
    @ApiModelProperty("使用地址")
    private String address;

    /**
     * 使用时间/用工时间
     */
    @ApiModelProperty("使用时间/用工时间")
    private LocalDateTime time;

    /**
     * 用工时长（天）
     */
    @ApiModelProperty("用工时长（天）")
    private String duration;

    /**
     * 用工人数
     */
    @ApiModelProperty("用工人数")
    private Integer person;

    /**
     * 创建时间
     */
    @ApiModelProperty("")
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @ApiModelProperty("")
    private Integer createBy;

    /**
     * 修改时间
     */
    @ApiModelProperty("")
    private LocalDateTime updateDate;

    /**
     * 修改人
     */
    @ApiModelProperty("")
    private Integer updateBy;


}
