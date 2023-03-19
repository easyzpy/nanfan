package com.randing.system.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step8SelfExamination implements Serializable {
    private static final long serialVersionUID = 3524341282884562276L;



    /**
     * 推广个数（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("推广个数（新品种在崖州区成果转化个数）")
    private Integer promotionNumber;

    /**
     * 累计推广面积（亩）（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("累计推广面积（亩）（新品种在崖州区成果转化个数）")
    private Double promotionArea;

    /**
     * 累计效益（万元）（新品种在崖州区成果转化个数）
     */
    @ApiModelProperty("累计效益（万元）（新品种在崖州区成果转化个数）")
    private Double promotionMoney;

    /**
     * 推广个数（新品种在全国成果转化个数）
     */
    @ApiModelProperty("推广个数（新品种在全国成果转化个数）")
    private Integer countryPromotionNumber;

    /**
     * 累计推广面积（亩）（新品种在全国成果转化个数）
     */
    @ApiModelProperty("累计推广面积（亩）（新品种在全国成果转化个数）")
    private Double countryPromotionArea;

    /**
     * 累计效益（万元）（新品种在全国成果转化个数）
     */
    @ApiModelProperty("累计效益（万元）（新品种在全国成果转化个数）")
    private Double countryPromotionMoney;

    /**
     * 科研投入（万元）
     */
    @ApiModelProperty("科研投入（万元）")
    private Double scientificResearchInvestment;


}
