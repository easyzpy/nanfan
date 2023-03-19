package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class SelfExaminationCropType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 作物类型及种植面积表id
     */
    private String cropTypeId;

    /**
     * 种植类型
     */
    private String cropType;

    /**
     * 种植面积
     */
    private Double cropTypeArea;

    /**
     * 南繁单位自查信息统计表id
     */
    private String selfExaminationId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime addTime;

    /**
     * 创建用户id
     */
    private String addPeople;

    /**
     * 最后一次修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 最后一次修改用户id
     */
    private String updatePeople;


}
