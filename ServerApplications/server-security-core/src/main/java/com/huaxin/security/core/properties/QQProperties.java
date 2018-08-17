package com.huaxin.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/17
 */
public class QQProperties extends SocialProperties {

    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
