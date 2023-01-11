package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 项目队列
 * </p>
 *
 * @author Leen
 * @since 2022-12-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class PrUnblindingRelation implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 项目id
     */
      private Long prId;

      /**
     * 队列id
     */
      private Long cohortId;

      /**
     * 用户id
     */
      private Long userId;

      /**
     * 揭盲理由
     */
      private String reason;

      /**
     * 创建人
     */
      private String createBy;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;


}
