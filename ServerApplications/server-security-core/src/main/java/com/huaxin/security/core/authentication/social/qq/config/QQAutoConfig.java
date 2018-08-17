package com.huaxin.security.core.authentication.social.qq.config;

import com.huaxin.security.core.authentication.social.qq.connect.QQConnectionFactory;
import com.huaxin.security.core.properties.QQProperties;
import com.huaxin.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/17
 */
@Configuration
// 没有配置app-id 时不生效
@ConditionalOnProperty(prefix = "huaxin.security.social.qq", name="app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
