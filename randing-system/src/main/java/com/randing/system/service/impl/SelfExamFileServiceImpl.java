package com.randing.system.service.impl;

import com.randing.common.exception.BaseException;
import com.randing.common.utils.file.FileUploadUtils;
import com.randing.common.utils.uuid.UUID;
import com.randing.system.domain.po.SelfExamFile;
import com.randing.system.mapper.SelfExamFileMapper;
import com.randing.system.service.ISelfExamFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-04-05
 */
@Service
@Slf4j
public class SelfExamFileServiceImpl extends ServiceImpl<SelfExamFileMapper, SelfExamFile> implements ISelfExamFileService {
    @Value("${tomcatBasedir}")
    public String tomcatBasedir;
    private static final String commonFilePath = "/common/image/selfexam/";
    @Override
    public SelfExamFile uploadFile(MultipartFile file) {
//        if (selfExaminationId == null) {
//            throw new BaseException("参数错误");
//        }
//        try {
//            int i = Integer.parseInt(selfExaminationId);
//        } catch (NumberFormatException e) {
//            throw new BaseException("参数错误1");
//        }

        String extension = FileUploadUtils.getExtension(file);
        if (!"pdf".equals(extension) &&!"jpg".equals(extension) && !"png".equals(extension) && !"jpeg".equals(extension)&&!"gif".equals(extension)) {
            throw new BaseException("仅允许图片和pdf");
        }
        String fileName = tomcatBasedir + commonFilePath + "base/" + extension + "/" + UUID.randomUUID();
        SelfExamFile selfExamFile = new SelfExamFile();
        selfExamFile.setFileId(UUID.randomUUID().toString());
        selfExamFile.setFileName(file.getOriginalFilename());
        selfExamFile.setFileUrl(fileName.substring(fileName.indexOf(commonFilePath)));
//        selfExamFile.setSelfExaminationId(selfExaminationId);
        baseMapper.insert(selfExamFile);
        File dest = new File(fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        log.info("filePath:{}", dest.getAbsolutePath());
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new BaseException("上传失败");
        }
        return selfExamFile;
    }
}
