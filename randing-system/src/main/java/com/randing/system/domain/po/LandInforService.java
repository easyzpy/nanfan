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
    public class LandInforService implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 土地信息id
     */
      private Integer inforId;

      /**
     * 土地服务id
     */
      private Integer serviceId;


}
