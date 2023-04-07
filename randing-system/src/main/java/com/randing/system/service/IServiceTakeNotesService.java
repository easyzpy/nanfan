package com.randing.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ServiceTakeNotes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.randing.system.domain.po.ServiceTaskNotesMoeny;
import com.randing.system.domain.vo.ServiceNoteReqDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-04-06
 */
public interface IServiceTakeNotesService extends IService<ServiceTakeNotes> {

    Page<ServiceTakeNotes> listPage(ServiceNoteReqDTO serviceNoteReqDTO);

    List<ServiceTaskNotesMoeny> getNotesMoeny(String serviceTakeNotesId);
}
