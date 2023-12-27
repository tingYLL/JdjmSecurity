package com.jdjm.zhy.controller;

import com.jdjm.zhy.entity.User;
import com.jdjm.zhy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index() {
        System.out.println("hello index");
        return "hello index";
    }

    @RequestMapping("/getUser")
    public User queryUser(@RequestParam(value="userName") String userName){
        User user = userService.queryUser(userName);
        return user;
    }

}
