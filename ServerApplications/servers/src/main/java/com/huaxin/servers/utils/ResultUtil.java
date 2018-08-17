package com.huaxin.servers.utils;

import com.huaxin.servers.response.ResultMsg;

import static com.huaxin.servers.constants.ReturnCode.SUCCESS_CODE;
import static com.huaxin.servers.constants.ReturnCode.SUCCESS_MSG;

/**
 * @Author: XiongChi
 * @Description: 返回值工具类
 * @Date: 2018/8/14
 */
public class ResultUtil {

    public static ResultMsg success(Object o){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(SUCCESS_CODE);
        resultMsg.setMsg(SUCCESS_MSG);
        resultMsg.setContent(o);
        return resultMsg;
    }

    public static ResultMsg error(String code, String msg){
        ResultMsg resultMsg = new ResultMsg();
        resultMsg.setCode(code);
        resultMsg.setMsg(msg);
        return resultMsg;
    }

}
