package com.huaxin.servers.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/15
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        log.info("表单登陆用户名" + s);
        //TODO: 数据库查找用户信息
        //passwordEncoder.encode("123456")
        return new User(s, passwordEncoder.encode("123456"),
                true,//可用
                true,//是否过期
                true,//
                true,//是否锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Override
    public SocialUserDetails loadUserByUserId(String s) throws UsernameNotFoundException {
        log.info("社交用户登陆id" + s);
        //TODO: 数据库查找用户信息
        //passwordEncoder.encode("123456")
        return new SocialUser(s, passwordEncoder.encode("123456"),
                true,//可用
                true,//是否过期
                true,//
                true,//是否锁定
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
