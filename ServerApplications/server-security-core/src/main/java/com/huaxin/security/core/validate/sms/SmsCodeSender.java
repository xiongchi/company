package com.huaxin.security.core.validate.sms;

/**
 * @Author: XiongChi
 * @Description: 短信发送
 * @Date: 2018/8/16
 */
public interface SmsCodeSender {

    void send(String mobile, String code);

}
