package com.spring.security.test.demo.provider;

import com.spring.security.test.demo.service.SpringUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hutianchen
 * @Desc 该方法只在单例服务器中使用、暂时可以忽略
 */
@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    SpringUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication, "非验证类型");
        //账号
        String username = authentication.getName();
        //密码
        String password = authentication.getCredentials().toString();
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        if(!password.equals(userDetails.getPassword())){
            throw  new UsernameNotFoundException("用户名或密码错误");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}