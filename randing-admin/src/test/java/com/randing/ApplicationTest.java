package com.randing;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyBatch;
import com.randing.system.domain.po.ApplyObjection;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;
import com.randing.system.mapper.ApplyBatchMapper;
import com.randing.system.service.IApplyObjectionService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApplicationTest {
    @Autowired
    private IApplyObjectionService applyObjectionService;
    @Resource
    private ApplyBatchMapper applyBatchMapper;

    @Test
    public void test02() {
//        ApplyBatch applyBatch = applyBatchMapper.selectById(11);
//        System.out.println(applyBatch);
        ApplyBatch applyBatch = new ApplyBatch();
        applyBatch.setId(11L);
        applyBatch.setBatchName("2023年第2批");
        //ApplyBatch(id=11, batchId=1bdde9e1e4824ffb9c67b9fbe2e6aa90, batchName=2023年第2批次, startTime=2023-04-13T08:00, endTime=2023-06-30T08:00, createDate=2023-03-15T10:49:38, createUser=7, updateDate=2023-06-09T15:59:31, updateUser=9, activity=1)
//        applyBatchMapper.updateById(applyBatch);

    }
    @Test
    public void test01() {
        ApplyObjectionReqDTO dto = new ApplyObjectionReqDTO();
        dto.setPage(0L);
        dto.setPageSize(10L);
        Page<ApplyObjection> list = applyObjectionService.getList(dto);
        System.out.println();
    }
    @Test
    public void test25() {

        Configuration configuration = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        // 模板文件所在路径
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        Template t = null;
        try {
            // 获取模板文件
            t = configuration.getTemplate("123.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("text1", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
        // 导出文件
        File outFile = new File("C:\\Users\\28544\\Desktop\\"+ UUID.randomUUID().toString()+".doc");
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8))) {
            if (t != null) {
                // 将填充数据填入模板文件并输出到目标文件
                t.process(dataMap, out);
            }
        } catch (IOException | TemplateException e1) {
            e1.printStackTrace();
        }
    }

}
