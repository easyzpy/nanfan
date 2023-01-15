package com.randing.system.service.impl;

import com.randing.system.domain.po.User;
import com.randing.system.mapper.UserMapper;
import com.randing.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
