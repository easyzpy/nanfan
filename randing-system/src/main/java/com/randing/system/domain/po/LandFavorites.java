package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2023-02-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandFavorites implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 土地id
     */
    private Long landId;

    /**
     * 土地名
     */
    private String landName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
