package com.jdjm.zhy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    //注意看,这里用的是@Controller 访问这个路径的时候，默认规则是 会跳转到templates下的 xxx.html 这里写的是login，则就是跳转到login.html,即我们自定义的登陆界面
    @RequestMapping("/login.htmll")
    public String login() {
        return "login";
    }

}
