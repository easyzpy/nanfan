package com.randing.system.service.impl;

import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.StringUtils;
import com.randing.common.utils.uuid.IdUtils;
import com.randing.system.domain.po.Unit;
import com.randing.system.domain.po.User;
import com.randing.system.mapper.UserMapper;
import com.randing.system.service.IUnitService;
import com.randing.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Autowired
    private IUnitService unitService;

    @Override
    public int bindUnit(Unit unit) {
        if (unit == null) {
            throw new BaseException("参数错误");
        }
        //单位不存在, 新增单位
        if (StringUtils.isEmpty(unit.getUnitId())) {
            unit.setUnitId(IdUtils.getUUID());
            unitService.save(unit);
        }
        Long loginUserId = LoginUser.getLoginUserId();
        User user = new User();
        user.setId(loginUserId);
        user.setUnit(unit.getUnitId());
        saveOrUpdate(user);
        return 0;
    }
}
