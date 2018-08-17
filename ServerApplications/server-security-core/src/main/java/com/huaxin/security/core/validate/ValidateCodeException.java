package com.huaxin.security.core.validate;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: XiongChi
 * @Description: 验证码异常
 * @Date: 2018/8/15
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String explanation) {
        super(explanation);
    }
}
