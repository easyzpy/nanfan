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
 * @since 2023-04-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class LandApplyOper implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 申请id
     */
      private Integer applyFormId;

      /**
     * 用户id
     */
      private Integer userId;

      /**
     * 操作类型（如：用户申请、用户审批等）
     */
      private String operType;

      /**
     * 操作内容（如：修改项目、退回项目等）
     */
      private String operContent;

      /**
     * 操作时间
     */
      private LocalDateTime operTime;


}
