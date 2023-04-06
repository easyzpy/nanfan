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
 * @since 2023-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SelfBaseFileRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户申请id
     */
    private String baseId;

    /**
     * 土地信息id
     */
    private String fileId;


}
