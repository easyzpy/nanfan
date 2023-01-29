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
 * @since 2023-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandContractContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 批次id
     */
    private String batchId;

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 合同字段
     */
    private String keyName;

    /**
     * 合同字段的内容
     */
    private String value;

    /**
     * 描述
     */
    private String mark;


}
