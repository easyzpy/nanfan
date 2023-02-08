package com.randing;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.randing.system.domain.po.ApplyObjection;
import com.randing.system.domain.vo.ApplyObjectionReqDTO;
import com.randing.system.service.IApplyObjectionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ApplicationTest {
    @Autowired
    private IApplyObjectionService applyObjectionService;
    @Test
    public void test01() {
        ApplyObjectionReqDTO dto = new ApplyObjectionReqDTO();
        dto.setPage(0L);
        dto.setPageSize(10L);
        Page<ApplyObjection> list = applyObjectionService.getList(dto);
        System.out.println();
    }
}
