package com.spring.security.test.demo.config;

import com.spring.security.test.demo.filter.JwtAuthenticationTokenFilter;
import com.spring.security.test.demo.handler.AjaxAccessDeniedHandler;
import com.spring.security.test.demo.service.SpringUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author hutianchen
 * @Date 2019 04-26
 * 重写security的configure方法，去除http基本验证
 */
@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {


//    @Autowired
//     SelfAuthenticationProvider selfAuthenticationProvider;
//    @Autowired
//     CustomAuthSuccessRedirectHandler customAuthSuccessRedirectHandler;
    @Autowired
    AjaxAccessDeniedHandler accessDeniedHandler;
    @Autowired
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    SpringUserDetailsService springUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
//        auth.authenticationProvider(selfAuthenticationProvider);
//        auth.userDetailsService(springUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 配置http基础校验，antMatchers匹配优先级按照调用链顺序从高到低
     * @param http
     * @throws Exception
     */
    @Override
        protected void configure(HttpSecurity http) throws Exception {
            //禁用csrf校验
            http.csrf().disable()
                    // 使用 JWT，关闭token
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    // /admin路径需要admin角色
//                    .antMatchers("/admin").hasRole("admin")
                    //动态权限配置
                    .anyRequest().access("@rbacauthorityservice.hasPermission(request,authentication)")
                    //指定登录方法名称，调用自定义安全提供者
//                    .and().formLogin()
//                    .loginPage("/login")
                    //登录授权成功处理器
//                    .successHandler(customAuthSuccessRedirectHandler)
            ;
            //"授权失败"处理器
             http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
            //添加jwt过滤器
            http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
            //禁用headers缓存
            http.headers().cacheControl();

        }

    }