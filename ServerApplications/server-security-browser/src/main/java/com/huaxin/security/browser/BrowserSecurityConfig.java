package com.huaxin.security.browser;

import com.huaxin.security.core.authentication.mobile.SmsAuthenticationConfig;
import com.huaxin.security.core.properties.SecurityProperties;
import com.huaxin.security.core.validate.img.ImageCodeFilter;
import com.huaxin.security.core.validate.sms.SmsCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Author: XiongChi
 * @Description: 安全配置
 * @Date: 2018/8/15
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler serverAuthenticationSuccessHandler;

    @Autowired
    private SmsAuthenticationConfig smsAuthenticationConfig;

    @Autowired
    private AuthenticationFailureHandler serverAuthenticationFailHandler;

    @Autowired
    private UserDetailsService myUserDetailsService;

    @Autowired
    private DataSource dataSource;

    // 社交登陆接口
    @Autowired
    private SpringSocialConfigurer severSpringSocialConfigurer;

    /**
     * 密码加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        // token存入数据库
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //第一次数据库中没表时 自动创建表
        //tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic()
        // 图片验证码过滤器
        ImageCodeFilter imageCodeFilter = new ImageCodeFilter();
        imageCodeFilter.setAuthenticationFailureHandler(serverAuthenticationFailHandler);
        imageCodeFilter.setSecurityProperties(securityProperties);
        imageCodeFilter.afterPropertiesSet();

        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(serverAuthenticationFailHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        http.addFilterBefore(imageCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                     .loginPage("/authentication/require")
                     .loginProcessingUrl("/authentication/form")
                     .successHandler(serverAuthenticationSuccessHandler)
                     .failureHandler(serverAuthenticationFailHandler)
                     .and()
                .rememberMe()
                     .tokenRepository(persistentTokenRepository())
                     .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                     .userDetailsService(myUserDetailsService)
                .and()
                .authorizeRequests()
                //不需要认证
                .antMatchers(
                        "/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                //关闭跨站防护
                .and().csrf().disable()
                .apply(smsAuthenticationConfig)
                .and()
                .apply(severSpringSocialConfigurer);
    }
}
