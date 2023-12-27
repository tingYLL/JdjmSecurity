package com.jdjm.zhy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jdjm.zhy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    User queryUser(@Param(value = "userName") String userName);
}
