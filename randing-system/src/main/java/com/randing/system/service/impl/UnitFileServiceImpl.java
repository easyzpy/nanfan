package com.randing.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.uuid.IdUtils;
import com.randing.system.domain.po.Unit;
import com.randing.system.domain.po.UnitFile;
import com.randing.system.mapper.UnitFileMapper;
import com.randing.system.service.IUnitFileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
@Service
public class UnitFileServiceImpl extends ServiceImpl<UnitFileMapper, UnitFile> implements IUnitFileService {

    @Override
    public void saveUnitFile(Unit unit) {
        UnitFile unitFile = new UnitFile();
        unitFile.setUnitFileId(IdUtils.getUUID());
        unitFile.setUnitId(unit.getUnitId());
        unitFile.setFileUrl(unit.getFileUrl());
        unitFile.setFileName(unit.getFileName());
        unitFile.setFileType("1");
        unitFile.setCreateUser(LoginUser.getId().toString());
        this.save(unitFile);

    }
}
