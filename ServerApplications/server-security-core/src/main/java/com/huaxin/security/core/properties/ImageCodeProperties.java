package com.huaxin.security.core.properties;

/**
 * @Author: XiongChi
 * @Description: 图形验证码
 * @Date: 2018/8/15
 */
public class ImageCodeProperties {

    private int width = 80;

    private int height = 20;

    private int length = 4;

    private String  urls;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }
}
