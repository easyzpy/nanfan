package com.randing.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.po.LandSevice;
import com.randing.system.domain.vo.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LandInforVo extends BasePage {

    /**
     * 土地序号
     */
    @ApiModelProperty("土地序号")
    private String landNumber;

    /**
     * 土地名称
     */
    @ApiModelProperty("土地名称")
    private String landName;

    /**
     * 土地最低价
     */
    @ApiModelProperty("土地最低价")
    private Double landPrice;

    /**
     * 土地最高价
     */
    @ApiModelProperty("土地最高价")
    private Double landMaxPrice;

    /**
     * 土地总面积
     */
    @ApiModelProperty("土地总面积")
    private Double landAreaTotal;
    /*查询字段start*/
    /**
     * 土地总面积start
     */
    @ApiModelProperty("土地总面积start")
    private Double landAreaTotalStart;

    /**
     * 土地总面积end
     */
    @ApiModelProperty("土地总面积end")
    private Double landAreaTotalEnd;
    /**
     * 土地可用面积start
     */
    @ApiModelProperty("土地可用面积start")
    private Double landAreaSurplusStart;
    /**
     * 土地可用面积end
     */
    @ApiModelProperty("土地可用面积end")
    private Double landAreaSurplusEnd;
    /**
     * 土地已用面积start
     */
    @ApiModelProperty("土地已用面积start")
    private Double landAreaUsableStart;
    /**
     * 土地已用面积end
     */
    @ApiModelProperty("土地已用面积end")
    private Double landAreaUsableEnd;
    /*查询字段end*/
    /**
     * 土地可用面积
     */
    @ApiModelProperty("土地可用面积")
    private Double landAreaSurplus;

    /**
     * 土地已用面积
     */
    @ApiModelProperty("土地已用面积")
    private Double landAreaUsable;

    /**
     * 土地地块位置
     */
    @ApiModelProperty("土地地块位置")
    private String landLocation;

    /**
     * 土地地块gis标记
     */
    @ApiModelProperty("土地地块gis标记")
    private Blob loadGisSign;

    /**
     * 土地VR链接地址
     */
    @ApiModelProperty("土地VR链接地址")
    private String landVrUrl;

    /**
     * 水利设施
     */
    @ApiModelProperty("水利设施")
    private String landWater;

    /**
     * 土壤类型
     */
    @ApiModelProperty("土壤类型")
    private String landSoilType;

    /**
     * 道路设施
     */
    @ApiModelProperty("道路设施")
    private String landRoad;

    /**
     * 土壤性质
     */
    @ApiModelProperty("土壤性质")
    private String landSoilNature;

    /**
     * 土壤肥力
     */
    @ApiModelProperty("土壤肥力")
    private String landSoilFertility;

    /**
     * 土壤酸碱性
     */
    @ApiModelProperty("土壤酸碱性")
    private String landSoilAcidBase;

    /**
     * 仓储用地
     */
    @ApiModelProperty("仓储用地")
    private String landStorage;

    /**
     * 其他
     */
    @ApiModelProperty("其他")
    private String landOther;

    /**
     * 晾晒用地
     */
    @ApiModelProperty("晾晒用地")
    private String landAirDrying;

    /**
     * 土地主图路径
     */
    @ApiModelProperty("土地主图路径")
    private String landImgUrl;

    /**
     * 作物类型
     */
    @ApiModelProperty("作物类型")
    private String landCropType;

    /**
     * 土地申请阈值，超过则在用户申请时，弹出提示信息
     */
    @ApiModelProperty("土地申请阈值，超过则在用户申请时，弹出提示信息")
    private String landApplyThreshold;

    /**
     * 发布时间
     */
    @ApiModelProperty("发布时间")
    private LocalDateTime landReleaseTime;

    /**
     * 创建信息时间
     */
    @ApiModelProperty("创建信息时间")
    private LocalDateTime landCreateTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private Integer landCreateUserId;

    /**
     * 最近一次的更新时间
     */
    @ApiModelProperty("最近一次的更新时间")
    private LocalDateTime landUpdateTime;

    /**
     * 最近一次的修改人
     */
    @ApiModelProperty("最近一次的修改人")
    private Integer landUpdateUserId;

    private String landPeriphery;

    private String landType;

    /**
     * 地块序号
     */
    @ApiModelProperty("地块序号")
    private Integer landSequence;

    /**
     * 土地有机质
     */
    @ApiModelProperty("土地有机质")
    private String landOrganic;

    /**
     * 土地全盐量
     */
    @ApiModelProperty("土地全盐量")
    private String landSalt;

    /**
     * 面积
     */
    @ApiModelProperty("土壤面积排序")
    private OrderByEnum landAreaTotalOrder;
    /**
     * 面积
     */
    @ApiModelProperty("最低单价排序")
    private OrderByEnum landPriceOrder;
    /**
     * 面积
     */
    @ApiModelProperty("最高单价")
    private OrderByEnum landMaxPriceOrder;
    /**
     *  所属区域管理:三亚,陵水
     */
    @ApiModelProperty("所属区域管理:三亚,陵水")
    private String landAscription;

    /**
     * 面积
     */
    @ApiModelProperty("发布时间排序")
    private OrderByEnum landReleaseTimeOrder;

    @ApiModelProperty("")
    @TableField(exist = false)
    private List<Long> ids;

    @TableField(exist = false)
    private Integer favoriteStatus;

    @TableField(exist = false)
    private List<LandSevice> landServices;



}
