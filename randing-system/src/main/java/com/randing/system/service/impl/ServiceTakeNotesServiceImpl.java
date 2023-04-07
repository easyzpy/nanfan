package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.LandRetreat;
import com.randing.system.domain.po.ServiceTakeNotes;
import com.randing.system.domain.po.ServiceTaskNotesMoeny;
import com.randing.system.domain.vo.ServiceNoteReqDTO;
import com.randing.system.mapper.ServiceTakeNotesMapper;
import com.randing.system.service.IServiceTakeNotesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.system.service.IServiceTaskNotesMoenyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-04-06
 */
@Service
public class ServiceTakeNotesServiceImpl extends ServiceImpl<ServiceTakeNotesMapper, ServiceTakeNotes> implements IServiceTakeNotesService {

    @Autowired
    private IServiceTaskNotesMoenyService serviceTaskNotesMoenyService;
    @Override
    public Page<ServiceTakeNotes> listPage(ServiceNoteReqDTO serviceNoteReqDTO) {
        return baseMapper.selectPage(new Page<>(serviceNoteReqDTO.getPage(), serviceNoteReqDTO.getPageSize()), Wrappers.lambdaQuery(ServiceTakeNotes.class));
    }

    @Override
    public List<ServiceTaskNotesMoeny> getNotesMoeny(String serviceTakeNotesId) {
        return serviceTaskNotesMoenyService.list(Wrappers.lambdaQuery(ServiceTaskNotesMoeny.class).eq(ServiceTaskNotesMoeny::getServiceTakeNotesId, serviceTakeNotesId));
    }

}
