package com.randing.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.LandInfor;
import com.randing.system.domain.vo.LandInforVo;
import com.randing.system.service.ILandInforService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
 *  前端控制器
 * </p>
 *
 * @author Leen
 * @since 2022-12-15
 */
@RestController
@RequestMapping("/landInfor")
@Api("土地信息")
public class LandInforController {
    @Autowired
    private ILandInforService iLandInforService;

    @PostMapping("listPage")
    @ApiOperation("用地信息列表")
    public AjaxResult<Page<LandInfor>> listPage(@RequestBody LandInforVo landInforVo) {
        return AjaxResult.success(iLandInforService.listPage(landInforVo));
    }

    @GetMapping("findById")
    @ApiOperation("土地信息详情")
    public AjaxResult<LandInforVo> findById(@RequestParam("id") Long id) {
        return AjaxResult.success(iLandInforService.findById(id));
    }
    @GetMapping("landAscriptionList")
    @ApiOperation("所属区域列表 eg. 三亚,陵水")
    public AjaxResult<List<String>> landAscriptionList() {
        return AjaxResult.success(iLandInforService.landAscriptionList());
    }
}

