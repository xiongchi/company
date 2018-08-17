package com.huaxin.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/15
 */
@ConfigurationProperties(prefix = "huaxin.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();

    private SocialProperties social = new SocialProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
