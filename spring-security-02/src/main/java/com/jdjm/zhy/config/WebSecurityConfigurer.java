package com.jdjm.zhy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.htmll").permitAll()  //permitAll()表示放行
                .mvcMatchers("/index").permitAll() //放行资源写在任何(即anyRequest)前面,不然会报错
                .anyRequest().authenticated() //authenticated() 需要认证
                .and()
                .formLogin()
                .loginPage("/login.htmll") //用来指定默认登录页面,不指定的话，当用户访问未认证资源的时候，依旧会跳转到默认Security的默认登陆页面
                .loginProcessingUrl("/doLogin")
                /**
                 * ↑ 一旦指定默认登录页面 ,就必须写这个 ;这里的参数(即"/doLogin") 和 templates下的那个前端文件里的表单发送的路径必须一样
                 * 在后端Controller中可以没有实际的/doLogin，只需要和 templates下的那个前端文件里的表单发送的路径一样即可
                 */
                .usernameParameter("uname")
                .passwordParameter("passwd")
//                .successForwardUrl("/index") 		 //forward 跳转           注意:不会跳转到之前请求路径
                //.defaultSuccessUrl("/index")   //redirect 重定向    注意:如果之前请求路径,会有优先跳转之前请求路径
                .successHandler(myAuthenticationSuccessHandler) //前后端分离的情况下，登陆成功后不是跳转到一个新的页面资源，而是返回json数据
                .and()
                .csrf().disable();

    }
}
