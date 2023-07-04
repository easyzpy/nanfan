package com.randing;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyBatch;
import com.randing.system.domain.po.ApplyObjection;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;
import com.randing.system.mapper.ApplyBatchMapper;
import com.randing.system.service.IApplyObjectionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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
}
