package com.huaxin.servers.response;

import java.io.Serializable;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/14
 */
public class ResultMsg implements Serializable {

    private String code;

    private String msg;

    private Object content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
