package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 退地附件
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandRetreatFile implements Serializable {

    private Long id;

    private static final long serialVersionUID = 1L;

    /**
     * 退地申请附件材料id
     */
    private String retreatFileId;

    /**
     * 退地申请表id
     */
    private String landRetreatId;

    /**
     * 材料Url路径
     */
    private String fileUrl;

    /**
     * 材料名称
     */
    private String fileName;

    /**
     * 材料类别（1：保育材料，2：有机质量材料，3：绿色发展材料，4：土地恢复措施，5：劳务合同，6：退地方案）
     */
    private Integer fileType;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 添加的用户id
     */
    private String addUser;


}
