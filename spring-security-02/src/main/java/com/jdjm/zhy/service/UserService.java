package com.jdjm.zhy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jdjm.zhy.entity.User;

public interface UserService extends IService<User>{
    User queryUser(String userName);
}
