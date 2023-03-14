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
 * @since 2023-03-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class LandSevice implements Serializable {

    private static final long serialVersionUID = 1L;
  private Long id;
      /**
     * 服务名称
     */
      private String serviceName;


}
