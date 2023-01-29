package com.randing.web.controller;


import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.LandRetreat;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.service.ILandRetreatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  退地controller
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
@RestController
@Api("退地")
@RequestMapping("/landRetreat")
public class LandRetreatController {

    @Autowired
    private ILandRetreatService landRetreatService;


    @PostMapping("listPage")
    @ApiOperation("退地列表")
    public AjaxResult<List<LandRetreat>> listPage(@RequestBody LandRetreat landRetreat) {
        return AjaxResult.success(landRetreatService.listPage(landRetreat));
    }

    @GetMapping("findById")
    @ApiOperation("退地详情")
    public AjaxResult<LandRetreat> findById(@RequestParam("id") Long id) {
        return AjaxResult.success(landRetreatService.findById(id));
    }

}

