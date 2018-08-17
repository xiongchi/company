package com.huaxin.security.core.validate.sms;

import java.time.LocalDateTime;

/**
 * @Author: XiongChi
 * @Description: 短信验证码
 * @Date: 2018/8/15
 */
public class SmsCode {

    private String code;

    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    // 是否过期
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
