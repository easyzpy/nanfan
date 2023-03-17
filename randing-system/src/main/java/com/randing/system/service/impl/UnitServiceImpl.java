package com.randing.system.service.impl;

import com.randing.common.utils.uuid.UUID;
import com.randing.system.domain.po.Unit;
import com.randing.system.mapper.UnitMapper;
import com.randing.system.service.IUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-03-15
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements IUnitService {
    private static final String socialCodeRex = "^([0-9A-HJ-NPQRTUWXY]{2}\\d{6}[0-9A-HJ-NPQRTUWXY]{10}|[1-9]\\d{14})$";


    @Transactional
    public int insert(Unit unit) {


        unit.setUnitId(UUID.randomUUID().toString().replaceAll("-", ""));
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        Pattern compile = Pattern.compile(socialCodeRex);
        System.out.println(compile.matcher("9135052155323005XL").matches());
        System.out.println(compile.matcher("91460200MA5T89HQ12").matches());
        System.out.println(compile.matcher("91350526557596032H").matches());
        System.out.println(compile.matcher("12460200MB1D76498X").matches());
        System.out.println(compile.matcher("12460200MB1E85571Y").matches());
        System.out.println(compile.matcher("91110000100000438C").matches());
    }
}
