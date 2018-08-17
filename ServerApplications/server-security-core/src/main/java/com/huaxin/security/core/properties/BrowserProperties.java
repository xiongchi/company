package com.huaxin.security.core.properties;

/**
 * @Author: XiongChi
 * @Description: 浏览器属性
 * @Date: 2018/8/15
 */
public class BrowserProperties {

    private String loginPage = "/signIn.html";

    private LoginType loginType = LoginType.JSON;

    // 图形验证码
    private ImageCodeProperties imageCode = new ImageCodeProperties();

    //短信验证码
    private SmsCodeProperties smsCode = new SmsCodeProperties();

    private int rememberMeSeconds= 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}
