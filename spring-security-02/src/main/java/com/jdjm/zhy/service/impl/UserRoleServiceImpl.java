package com.jdjm.zhy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdjm.zhy.entity.UserRole;
import com.jdjm.zhy.mapper.UserRoleMapper;
import com.jdjm.zhy.service.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
