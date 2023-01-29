package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.vo.ApplyBatchVo;
import com.randing.system.service.IApplyBatchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Leen
 * @since 2022-12-21
 */
@RestController
@Api(value = "申请批次")
@RequestMapping("/applyBatch")
public class ApplyBatchController {

    @Autowired
    private IApplyBatchService applyBatchService;
    @GetMapping("getList")
    @ApiOperation("申请批次 列表")
    public AjaxResult<List<ApplyBatchVo>> getList() {
        return AjaxResult.success(applyBatchService.getList());
    }


}

