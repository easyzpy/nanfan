package com.randing.system.service.impl;

import com.randing.common.exception.BaseException;
import com.randing.common.utils.StringUtils;
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
import java.util.Arrays;

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

    private static final String virtualPath = "/common/image";
    private static final String commonFilePath = "/selfexam/";
    ///common/image/landApplyUnit/jpg
    private static final String applyLandFilePath = "/landApplyUnit/";
    private static final String unitFilePath = "/unit/";
    private static final String[] applyLandExtension = new String[]{"jpg", "jpeg", "png", "pdf", "pbg", "doc", "docx"};

    static {
        Arrays.sort(applyLandExtension);
    }

    @Override
    public SelfExamFile uploadUnitFile(MultipartFile file) {
        SelfExamFile upload = this.upload(file, unitFilePath);
        return upload;
    }
    @Override
    public SelfExamFile uploadApplyLandFile(MultipartFile file) {
        SelfExamFile upload = this.upload(file, applyLandFilePath);
        return upload;
    }
    @Override
    public SelfExamFile upload(MultipartFile file, String path) {
        String extension = FileUploadUtils.getExtension(file);
        if (Arrays.binarySearch(applyLandExtension, extension) < 0) {
            throw new BaseException("仅允许格式为" + StringUtils.join(applyLandExtension, ",") + "的文件");
        }
        String fileName = tomcatBasedir + path  + extension + "/" + UUID.randomUUID();
        SelfExamFile selfExamFile = new SelfExamFile();
        selfExamFile.setFileId(UUID.randomUUID().toString());
        selfExamFile.setFileName(file.getOriginalFilename());
        selfExamFile.setFileUrl(virtualPath + fileName.substring(fileName.indexOf(path)));
//        baseMapper.insert(selfExamFile);
        File dest = new File(fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        log.info("filePath:{}", dest.getAbsolutePath());
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("e", e);
            throw new BaseException("上传失败");
        }
        return selfExamFile;
    }
    @Override
    public SelfExamFile uploadFile(MultipartFile file) {

        String extension = FileUploadUtils.getExtension(file);
        if (!"pdf".equals(extension) &&!"jpg".equals(extension) && !"png".equals(extension) && !"jpeg".equals(extension)&&!"gif".equals(extension)) {
            throw new BaseException("仅允许图片和pdf");
        }
        String fileName = tomcatBasedir + commonFilePath + "base/" + extension + "/" + UUID.randomUUID();
        SelfExamFile selfExamFile = new SelfExamFile();
        selfExamFile.setFileId(UUID.randomUUID().toString());
        selfExamFile.setFileName(file.getOriginalFilename());
        selfExamFile.setFileUrl(virtualPath  +fileName.substring(fileName.indexOf(commonFilePath)));
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
            log.error("e", e);
            throw new BaseException("上传失败");
        }
        return selfExamFile;
    }
}
