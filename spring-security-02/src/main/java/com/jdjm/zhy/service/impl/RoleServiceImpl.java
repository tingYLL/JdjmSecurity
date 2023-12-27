package com.jdjm.zhy.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdjm.zhy.entity.Role;
import com.jdjm.zhy.mapper.RoleMapper;
import com.jdjm.zhy.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
