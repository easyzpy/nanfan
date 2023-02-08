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
 * @since 2023-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ApplyObjectionFile implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 附件id
     */
    private String fileId;

    /**
     * 异议id
     */
    private String objectionId;

    /**
     * 文件名称
     */
    private String objectionFileName;

    /**
     * 文件url地址
     */
    private String objectionUrl;

    /**
     * 发起时间
     */
    private LocalDateTime addTime;


}
