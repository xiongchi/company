package com.huaxin.servers.annotations;

import java.lang.annotation.*;

/**
 * @Author: XiongChi
 * @Description: controller 日志注解 用于记录接口的日志信息
 * @Date: 2018/8/14
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControlLog {
    String value();
}
