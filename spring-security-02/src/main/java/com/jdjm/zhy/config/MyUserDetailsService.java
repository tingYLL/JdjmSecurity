package com.jdjm.zhy.config;

import com.jdjm.zhy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

//@Component
public class MyUserDetailsService implements UserDetailsService {

    //这里使用构造注入
    private UserMapper userMapper;

    @Autowired
    public MyUserDetailsService(UserMapper userMapper){
        this.userMapper = userMapper;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userMapper.queryUser(username);
        return null;
    }



}
