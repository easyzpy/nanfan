package com.randing.system.service;

import com.randing.system.domain.po.LandRetreatFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-01-29
 */
public interface ILandRetreatFileService extends IService<LandRetreatFile> {


    List<LandRetreatFile> getArtificialById(String id, Integer fileType);
}
