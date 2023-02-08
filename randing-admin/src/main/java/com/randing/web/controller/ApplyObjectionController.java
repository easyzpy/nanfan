package com.randing.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.ApplyObjection;
import com.randing.system.domain.po.LandContract;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;
import com.randing.system.service.IApplyObjectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Leen
 * @since 2023-02-08
 */
@RestController
@Api("用地异议")
@RequestMapping("/applyObjection")
public class ApplyObjectionController {

    @Autowired
    private IApplyObjectionService applyObjectionService;
    @PostMapping("listPage")
    @ApiOperation("异议列表列表")
    public AjaxResult<Page<ApplyObjection> > listPage(@RequestBody ApplyObjectionReqDTO applyObjectionReqDTO) {
        return AjaxResult.success(applyObjectionService.getList(applyObjectionReqDTO));
    }
}

