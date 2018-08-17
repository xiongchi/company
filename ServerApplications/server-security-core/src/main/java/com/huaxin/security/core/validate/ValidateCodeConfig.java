package com.huaxin.security.core.validate;

import com.huaxin.security.core.properties.SecurityProperties;
import com.huaxin.security.core.validate.img.ImageCodeGenerator;
import com.huaxin.security.core.validate.sms.DefaultSmsCodeSender;
import com.huaxin.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: XiongChi
 * @Description: 验证码配置
 * @Date: 2018/8/16
 */
@Configuration
public class ValidateCodeConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     *  可扩展自定义图形验证码
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="imageCodeGenerator")
    public ValidateGenerator imageCodeGenerator(){
        ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
        imageCodeGenerator.setSecurityProperties(securityProperties);
        return imageCodeGenerator;
    }

    /**
     *  可扩展短信验证码
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name="smsCodeSender")
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }

}
