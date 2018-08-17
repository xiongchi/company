package com.huaxin.servers.controller;

import com.huaxin.servers.annotations.ControlLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/14
 */
@RestController
public class Test {

    @GetMapping("/test")
    @ControlLog("测试")
    public String test(){
        return "test";
    }

}
