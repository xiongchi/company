package com.huaxin.security.core.authentication.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/17
 */
public class ServerSpringSocialConfiger extends SpringSocialConfigurer {

    private String filterProcessUrl;

    public ServerSpringSocialConfiger(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessUrl);
        return (T) filter;
    }
}
