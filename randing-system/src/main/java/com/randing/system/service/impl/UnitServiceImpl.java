package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.StringUtils;
import com.randing.common.utils.uuid.UUID;
import com.randing.system.domain.po.Unit;
import com.randing.system.mapper.UnitMapper;
import com.randing.system.service.IUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
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


    public static boolean patternCreditCode(String registerCode) {
        Pattern compile = Pattern.compile(socialCodeRex);
        Matcher matcher = compile.matcher(registerCode);
        return matcher.matches();
    }

    @Override
    public List<Unit> findByLikeUnitName(Unit unit) {
        if (StringUtils.isEmpty(unit.getUnitName())) {
            throw new BaseException("参数错误");
        }
        return baseMapper.selectList(Wrappers.lambdaQuery(Unit.class)
                .like(StringUtils.isNotEmpty(unit.getUnitName()), Unit::getUnitName, unit.getUnitName())
//                .eq(StringUtils.isNotEmpty(unit.getCreditCode()), Unit::getCreditCode, unit.getCreditCode())
        );
    }
}
