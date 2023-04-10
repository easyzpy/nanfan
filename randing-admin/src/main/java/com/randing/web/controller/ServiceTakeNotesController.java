package com.randing.web.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.common.core.domain.AjaxResult;
import com.randing.system.domain.po.ServiceTakeNotes;
import com.randing.system.domain.po.ServiceTaskNotesMoeny;
import com.randing.system.domain.vo.NoteReqDTO;
import com.randing.system.service.IServiceTakeNotesService;
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
 * 前端控制器
 * </p>
 *
 * @author Leen
 * @since 2023-04-06
 */
@RestController
@RequestMapping("/serviceTakeNotes")
@Api("服务跟踪")
public class ServiceTakeNotesController {

    @Autowired
    private IServiceTakeNotesService serviceTakeNotesService;
    @PostMapping("listPage")
    @ApiOperation("服务跟踪列表")
    public AjaxResult<Page<ServiceTakeNotes>> listPage(@RequestBody NoteReqDTO serviceNoteReqDTO) {
        return AjaxResult.success(serviceTakeNotesService.listPage(serviceNoteReqDTO));
    }

//    @GetMapping("getNotesMoeny")
//    @ApiOperation("服务跟踪金额列表")
//    public AjaxResult<List<ServiceTaskNotesMoeny>> getNotesMoeny(@RequestParam("serviceTakeNotesId") String serviceTakeNotesId) {
//        return AjaxResult.success(serviceTakeNotesService.getNotesMoeny(serviceTakeNotesId));
//    }

}

