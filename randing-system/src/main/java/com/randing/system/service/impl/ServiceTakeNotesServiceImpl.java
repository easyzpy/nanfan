package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.utils.LoginUser;
import com.randing.system.domain.po.ServiceTakeNotes;
import com.randing.system.domain.po.ServiceTaskNotesMoeny;
import com.randing.system.domain.vo.NoteReqDTO;
import com.randing.system.mapper.ServiceTakeNotesMapper;
import com.randing.system.service.IServiceTakeNotesService;
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
    public Page<ServiceTakeNotes> listPage(NoteReqDTO serviceNoteReqDTO) {
        Long loginUserId = LoginUser.getLoginUserId();
        return baseMapper.selectPage(new Page<>(serviceNoteReqDTO.getPage(), serviceNoteReqDTO.getPageSize()), Wrappers.lambdaQuery(ServiceTakeNotes.class)
                .eq(loginUserId != null, ServiceTakeNotes::getCreateBy, loginUserId)

        );
    }

    @Override
    public List<ServiceTaskNotesMoeny> getNotesMoeny(String serviceTakeNotesId) {
        return serviceTaskNotesMoenyService.list(Wrappers.lambdaQuery(ServiceTaskNotesMoeny.class).eq(ServiceTaskNotesMoeny::getServiceTakeNotesId, serviceTakeNotesId));
    }

}
