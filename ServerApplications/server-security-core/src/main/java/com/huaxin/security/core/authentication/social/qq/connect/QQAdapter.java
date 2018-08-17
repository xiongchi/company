package com.huaxin.security.core.authentication.social.qq.connect;

import com.huaxin.security.core.authentication.social.qq.QQ;
import com.huaxin.security.core.authentication.social.qq.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/17
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试QQ是否通
     *
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo userInfo = qq.getUserInfo();
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {
        // do nothing
    }
}
