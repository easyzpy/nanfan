package com.randing.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.randing.common.exception.BaseException;
import com.randing.common.utils.LoginUser;
import com.randing.common.utils.StringUtils;
import com.randing.system.domain.po.Unit;
import com.randing.system.domain.po.UnitFile;
import com.randing.system.domain.po.User;
import com.randing.system.mapper.UserMapper;
import com.randing.system.service.IUnitFileService;
import com.randing.system.service.IUnitService;
import com.randing.system.service.IUserService;
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
    @Autowired
    private IUnitFileService unitFileService;

    @Override
    public int bindUnit(Unit unit) {
        if (unit == null) {
            throw new BaseException("参数错误");
        }
        //单位不存在, 新增单位
        if (StringUtils.isEmpty(unit.getUnitId())) {
            throw new BaseException("单位名称不能为空");
        }
        Long loginUserId = LoginUser.getId();
        User user = new User();
        user.setId(loginUserId);
        user.setUnit(unit.getUnitId());
        if (StringUtils.isNotEmpty(unit.getUserContractPhone())) {
            user.setContactPhone(unit.getUserContractPhone());
        }
        saveOrUpdate(user);

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId, loginUserId)
                .set(User::getUnit, unit.getUnitId());
        unit.setUpdateUser(loginUserId.toString());
        //更新Unit
        unitService.updateById(unit);
//        如果传了营业执照 干掉以前的 重新生成一个
        unitFileService.remove(Wrappers.lambdaQuery(UnitFile.class).eq(UnitFile::getUnitId, unit.getUnitId()));
        if (StringUtils.isNotEmpty(unit.getFileUrl()) && StringUtils.isNotEmpty(unit.getFileName())) {
            unitFileService.saveUnitFile(unit);
        }
        return 0;
    }

    @Override
    public User getUserInfo(Long userId) {
        User byId = this.getById(userId);
        if (byId.getUnit() != null) {
            Unit byId1 = unitService.getOne(Wrappers.lambdaQuery(Unit.class).eq(Unit::getUnitId, byId.getUnit()));
            byId.setUnitEntity(byId1);
        }
        return byId;
    }

}
