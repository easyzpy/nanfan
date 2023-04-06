package com.randing.system.domain.vo;

import com.randing.system.domain.common.OrderByEnum;
import com.randing.system.domain.vo.base.BasePage;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyObjectionReqDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 1694464933809557059L;

    @ApiModelProperty("异议状态：0 发起异议 1异议通过 2申请答辩")
    private Integer type;

    @ApiModelProperty("批次id 通过applybatch/getList获取")
    private String batchId;
}

