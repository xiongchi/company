package com.huaxin.security.core.authentication.social.qq.connect;

import com.huaxin.security.core.authentication.social.qq.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/17
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
