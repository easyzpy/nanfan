package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *申请批次
 * </p>
 *
 * @author Leen
 * @since 2022-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplyBatch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 批次id
     */
    @ApiModelProperty("批次id")
    private String batchId;

    /**
     * 批次名称
     */
    @ApiModelProperty("批次名称")
    private String batchName;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private LocalDateTime updateDate;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private String updateUser;

    private String activity;


}
