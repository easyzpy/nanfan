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
 *
 * </p>
 *
 * @author Leen
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfExamFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件id
     */
    private String fileId;

    /**
     * 单位自查信息id
     */
    @ApiModelProperty("单位自查信息id")
    private String selfExaminationId;

    /**
     * 附件路径
     */
    private String fileUrl;

    /**
     * 附件名称
     */
    private String fileName;

    private Integer type;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;


    /**
     * 用户id
     */
    private String createUser;


}
