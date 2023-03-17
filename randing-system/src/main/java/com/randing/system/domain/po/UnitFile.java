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
 * @since 2023-03-15
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UnitFile implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 单位附件id
     */
      private String unitFileId;

      /**
     * 单位id
     */
      private String unitId;

      /**
     * 附件路径
     */
      private String fileUrl;

      /**
     * 附件名称
     */
      private String fileName;

      /**
     * 附件类别(1:营业执照)
     */
      private String fileType;

      /**
     * 创建时间
     */
      private LocalDateTime createDate;

      /**
     * 用户id
     */
      private String createUser;


}
