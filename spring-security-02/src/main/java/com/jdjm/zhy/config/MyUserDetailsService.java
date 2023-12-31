package com.jdjm.zhy.config;

import com.jdjm.zhy.entity.User;
import com.jdjm.zhy.mapper.UserMapper;
import com.jdjm.zhy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 自定义一个类实现UserDetailsService 然后编写查询数据库中登陆信息的逻辑代码 最后注入到WebSecurityConfigurer 调用WebSecurityConfigurer中的configure完成配置
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    //这里使用构造注入
    private UserMapper userMapper;

    @Autowired
    public MyUserDetailsService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    private UserService userService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.queryUser(username);
        return user;
    }



}
