package com.huaxin.security.core.validate.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: XiongChi
 * @Description: 默认短信验证码发送
 * @Date: 2018/8/16
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void send(String mobile, String code) {
        log.info("向" + mobile + "手机发送验证码" + code);
    }
}
