package com.randing.system.domain.po;

import java.io.Serializable;

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
public class LandApplyInfor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户申请id
     */
    private Integer applyId;

    /**
     * 土地信息id
     */
    private Integer inforId;


}
