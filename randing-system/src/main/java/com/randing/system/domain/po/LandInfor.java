package com.randing.system.domain.po;

import java.time.LocalDateTime;
import java.sql.Blob;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Leen
 * @since 2022-12-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LandInfor implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 土地序号
     */
    private String landNumber;

    /**
     * 土地名称
     */
    private String landName;

    /**
     * 土地最低价
     */
    private Double landPrice;

    /**
     * 土地最高价
     */
    private Double landMaxPrice;

    /**
     * 土地总面积
     */
    private Double landAreaTotal;

    /**
     * 土地可用面积
     */
    private Double landAreaSurplus;

    /**
     * 土地已用面积
     */
    private Double landAreaUsable;

    /**
     * 土地地块位置
     */
    private String landLocation;

    /**
     * 土地地块gis标记
     */
    private String
            loadGisSign;

    /**
     * 土地VR链接地址
     */
    private String landVrUrl;

    /**
     * 水利设施
     */
    private String landWater;

    /**
     * 土壤类型
     */
    private String landSoilType;

    /**
     * 道路设施
     */
    private String landRoad;

    /**
     * 土壤性质
     */
    private String landSoilNature;

    /**
     * 土壤肥力
     */
    private String landSoilFertility;

    /**
     * 土壤酸碱性
     */
    private String landSoilAcidBase;

    /**
     * 仓储用地
     */
    private String landStorage;

    /**
     * 其他
     */
    private String landOther;

    /**
     * 晾晒用地
     */
    private String landAirDrying;

    /**
     * 土地主图路径
     */
    private String landImgUrl;

    /**
     * 作物类型
     */
    private String landCropType;

    /**
     * 土地申请阈值，超过则在用户申请时，弹出提示信息
     */
    private String landApplyThreshold;

    /**
     * 发布时间
     */
    private LocalDateTime landReleaseTime;

    /**
     * 创建信息时间
     */
    private LocalDateTime landCreateTime;

    /**
     * 创建人
     */
    private Integer landCreateUserId;

    /**
     * 最近一次的更新时间
     */
    private LocalDateTime landUpdateTime;

    /**
     * 最近一次的修改人
     */
    private Integer landUpdateUserId;

    private String landPeriphery;

    /**
     * 0空闲<p/>
     * 1锁定<p/>
     * 2使用中<p/>
     */
    private String landType;

    /**
     * 地块序号
     */
    private Integer landSequence;

    /**
     * 土地有机质
     */
    private String landOrganic;

    /**
     * 土地全盐量
     */
    private String landSalt;

    /**
     *  所属区域管理:三亚,陵水
     */
    private String landAscription;

    /**
     *  地块类型:
     *  1,可选地块  空闲<p/>
     *  2,项目用地  锁定<p/>
     *  3,不可选地块  使用中<p/>
     */
    private Integer landMold;


}
