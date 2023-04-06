package com.randing.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.LandContract;
import com.randing.system.domain.vo.LandContractDTO;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.service.ILandContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  合同
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@RestController
@Api("合同api")
@RequestMapping("/landContract")
public class LandContractController {
    @Autowired
    private ILandContractService landContractService;

    @PostMapping("listPage")
    @ApiOperation("合同列表")
    public AjaxResult<Page<LandContractDTO>> listPage(@RequestBody LandContract landContract) {
        return AjaxResult.success(landContractService.listPage(landContract));
    }

    @GetMapping("findById")
    @ApiOperation("合同详情")
    public AjaxResult<LandContract> findById(@RequestParam("id") Long id) {
        return AjaxResult.success(landContractService.findById(id));
    }
}

