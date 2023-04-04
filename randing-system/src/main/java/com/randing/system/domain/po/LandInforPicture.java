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
 * @since 2023-03-25
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class LandInforPicture implements Serializable {

    private static final long serialVersionUID = 1L;

      /**
     * 土地信息id
     */
      private Integer inforId;

      /**
     * 图片名称
     */
      private String pictureName;

      /**
     * 图片存储路径（原图）
     */
      private String pictureUrl;

      /**
     * 图片存储路径（中图）
     */
      private String pictureUrlSecon;

      /**
     * 图片存储路径（小图）
     */
      private String pictureUrlSmall;


}
