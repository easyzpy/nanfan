package com.randing.system.service;

import com.randing.system.domain.po.SelfExamFile;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Leen
 * @since 2023-04-05
 */
public interface ISelfExamFileService extends IService<SelfExamFile> {


    SelfExamFile uploadUnitFile(MultipartFile file);

    SelfExamFile uploadApplyLandFile(MultipartFile file);

    SelfExamFile upload(MultipartFile file, String path);

    SelfExamFile uploadFile(MultipartFile file);

}
