package com.huaxin.servers.aop;

import com.alibaba.fastjson.JSONObject;
import com.huaxin.servers.annotations.ControlLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: XiongChi
 * @Description:  记录日志信息
 * @Date: 2018/8/14
 */
@Aspect
@Component
public class LogAOP {
    private static Logger log = LoggerFactory.getLogger(LogAOP.class);

    @Pointcut("@annotation(com.huaxin.servers.annotations.ControlLog)")
    public void controllerLogPointCut(){
    }

    @Around("controllerLogPointCut()")
    public Object controllerLogAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature sign =  (MethodSignature)pjp.getSignature();
        Method method = sign.getMethod();
        ControlLog annotation = method.getAnnotation(ControlLog.class);
        // 记录开始时间
        long start = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        log.info("{}请求报文：{}",annotation.value(), JSONObject.toJSONString(args));

        Object result = pjp.proceed();
        log.info("{}返回报文：{}", annotation.value(), JSONObject.toJSONString(result));
        // 获取执行完的时间
        log.info("{}执行时长{}s", annotation.value(),(System.currentTimeMillis() - start)/1000.0);
        return result;
    }

}
