package com.jdjm.zhy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    //自定义数据源（基于内存）
    @Bean
    public UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        inMemoryUserDetailsManager.createUser(User.withUsername("root").password("{noop}123666").roles("admin").build());
        return inMemoryUserDetailsManager;
    }

    //自定义数据源（来自数据库中的查询结果）
    @Autowired
    private   MyUserDetailsService myUserDetailsService;

    //构造注入
//    private  final MyUserDetailsService myUserDetailsService;
//
//    @Autowired
//    public WebSecurityConfigurer(MyUserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
//        自定义数据源 （基于内存）
//        builder.userDetailsService(userDetailsService());

//        自定义数据源（来自数据库中的查询结果）
        builder.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.htmll").permitAll()  //permitAll()表示放行
                .mvcMatchers("/index").permitAll() //放行资源写在任何(即anyRequest)前面,不然会报错
//                .mvcMatchers("/getUser").permitAll()
                .anyRequest().authenticated() //authenticated() 需要认证
                .and()
                .formLogin()
                .loginPage("/login.htmll") //用来指定默认登录页面（意思就是访问受限资源的时候，如果还没登陆，就会跳转到这个页面下）,不指定的话，当用户访问未认证资源的时候，依旧会跳转到默认Security的默认登陆页面
                .loginProcessingUrl("/doLogin")
                /**
                 * ↑ 一旦指定默认登录页面 ,就必须写这个 ;这里的参数(即"/doLogin") 和 templates下的那个前端文件里的表单发送的路径必须一样
                 * 在后端Controller中可以没有实际的/doLogin，只需要和 templates下的那个前端文件里的表单发送的路径一样即可
                 */
                .usernameParameter("uname")
                .passwordParameter("passwd")
//                .successForwardUrl("/index") 		 //认证成功后 forward 跳转    路径变成/doLogin 页面显示的内容为  @RequestMapping("/index")返回的内容
//                .defaultSuccessUrl("/index")   // 认证成功后 redirect 重定向
                                                    //Tip:successForwardUrl 和 defaultSuccessUrl只能二选一 不过前后端分离的情况下一般是使用下面这种↓
                .successHandler(myAuthenticationSuccessHandler) //前后端分离的情况下，登陆成功后不是跳转到一个新的页面资源，而是返回json数据
//                .failureForwardUrl("/login.htmll") //认证失败后 forward跳转
//                .failureUrl("/login.htmll") //认证失败后 redirect跳转
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .logout()//获取对象 以便调用下面的方法
//                .logoutUrl("/logout") //指定退出登录请求地址(只需要在浏览器地址栏输入这个就行)，默认是 GET 请求，路径为 `/logout`
//                .invalidateHttpSession(true) // 退出时是否是 session 失效，默认值为 true
                .logoutRequestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher("/logout1","GET"),
                        new AntPathRequestMatcher("/logout","GET")
                ))
                .clearAuthentication(true) // 退出时是否清除认证信息，默认值为 true
//                .logoutSuccessUrl("/login.htmll") //退出登录时跳转地址
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .and()
                .csrf().disable();

    }
}
