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
public class KeepApplyReqDTO extends BasePage implements Serializable {
    private static final long serialVersionUID = 1694464933809557059L;

    @ApiModelProperty("批次id 通过applybatch/getList获取")
    private String batchId;
    @ApiModelProperty("状态(注意不是删除状态) 0 使用 1 删除 2 保存不提交 3 提交（待审批） 4 已评分（已评分未选地块）5 已退回  6异议中（发起异议） 7已选地（异议通过） 8异议中（异议退回） 9已选地块（已评分并选择地块）10确定地块（确定后只能发起合同签订，不能发起异议） 11合同已签订")
    private String delFlag;
    @ApiModelProperty("用地申请单位")
    private String landApplyUnit;
    @ApiModelProperty("排序字段")
    private String orderName;
    @ApiModelProperty("asc 或 desc")
    private OrderByEnum orderType;
    @ApiModelProperty("delflag arr")
    private List<String> delFlagArr;
}
