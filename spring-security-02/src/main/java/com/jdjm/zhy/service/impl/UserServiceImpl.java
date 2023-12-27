package com.jdjm.zhy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jdjm.zhy.entity.Role;
import com.jdjm.zhy.entity.User;
import com.jdjm.zhy.entity.UserRole;
import com.jdjm.zhy.mapper.UserMapper;
import com.jdjm.zhy.service.RoleService;
import com.jdjm.zhy.service.UserRoleService;
import com.jdjm.zhy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Override
    public User queryUser(String userName) {
        User user = userMapper.queryUser(userName);
        List<User> userList = this.lambdaQuery().eq(User::getUsername, userName).list();
        List<Integer> userIdList = userList.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<UserRole> userRoleList = userRoleService.lambdaQuery().in(UserRole::getUid, userIdList).list();
        List<Integer> roleIdList = userRoleList.stream().map(UserRole::getRid).collect(Collectors.toList());
        List<Role> roleList = roleService.lambdaQuery().in(Role::getId, roleIdList).list();
        user.setRoles(roleList);
        return user;
    }
}
