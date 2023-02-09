package com.randing.system.domain.vo;

import com.randing.system.domain.vo.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteReqDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 1694464933809557059L;

    //    @ApiModelProperty("异议状态：0 发起异议 1异议通过 2申请答辩")
//    private Integer type;
    private Long landInfoId;

//    private Long userId;
}
