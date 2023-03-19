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
 * @since 2023-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExaminationNewCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    /**
     * 新品类及个数id
     */
    @ApiModelProperty("新品类及个数id")
    private String newCategoryId;

    /**
     * 新品类
     */
    @ApiModelProperty("新品类")
    private String newCategory;

    /**
     * 个数
     */
    @ApiModelProperty("个数")
    private Integer newCategoryNumber;

    /**
     * 南繁自查信息统计表id
     */
    @ApiModelProperty("南繁自查信息统计表id")
    private String selfExaminationId;

    /**
     * 添加时间
     */
    @ApiModelProperty("")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 添加用户id
     */
    @ApiModelProperty("")
    private String addPeople;

    /**
     * 最后一次修改时间
     */
    @ApiModelProperty("")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 最后一次修改人
     */
    @ApiModelProperty("")
    private String updatePeople;


}
