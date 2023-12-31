package com.jdjm.zhy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdjm.zhy.entity.Role;
import com.jdjm.zhy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User queryUser(@Param(value = "userName") String userName);

    List<Role> queryRole(@Param(value="userId") Integer userId);

    List<Role> queryRole2(@Param(value = "uid") Integer uid);
}
