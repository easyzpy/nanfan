package com.randing.system.service;

import com.randing.system.domain.po.Unit;
import com.randing.system.domain.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Leen
 * @since 2023-01-15
 */
public interface IUserService extends IService<User> {

    int bindUnit(Unit unit);

    User getUserInfo(Long userId);
}
