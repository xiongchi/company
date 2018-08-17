package com.huaxin.security.core.validate.sms;

import com.huaxin.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XiongChi
 * @Description: 短信验证码
 * @Date: 2018/8/16
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements SmsValidateGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    public SmsCode generator(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getBrowser().getSmsCode().getLength());
        return new SmsCode(code, securityProperties.getBrowser().getSmsCode().getExpireIn());
    }
}
