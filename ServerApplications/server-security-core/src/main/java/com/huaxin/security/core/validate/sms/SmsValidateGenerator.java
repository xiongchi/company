package com.huaxin.security.core.validate.sms;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/16
 */
public interface SmsValidateGenerator {
    SmsCode generator(ServletWebRequest request);
}
