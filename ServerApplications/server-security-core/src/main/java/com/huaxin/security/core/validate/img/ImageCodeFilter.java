package com.huaxin.security.core.validate.img;

import com.huaxin.security.core.constans.SessionKey;
import com.huaxin.security.core.properties.SecurityProperties;
import com.huaxin.security.core.validate.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


/**
 * @Author: XiongChi
 * @Description: 验证过滤器  一次有效
 * @Date: 2018/8/15
 */
public class ImageCodeFilter extends OncePerRequestFilter{

    private AuthenticationFailureHandler authenticationFailureHandler;

    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    //解析出配置文件中需要图形验证码的url
    private Set<String> urls = new HashSet<>();

    // 路径匹配器
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 根据","解析出配置的url
        String[] configUrls =
                StringUtils.splitByWholeSeparatorPreserveAllTokens
                        (securityProperties.getBrowser().getImageCode().getUrls(), ",");

        if(configUrls != null){
            for (String url : configUrls){
                urls.add(url);
            }
        }

        boolean action = false;

        for(String url : urls){
            // 匹配配置url与请求url
            if(pathMatcher.match(url, request.getRequestURI())){
                action = true;
            }
        }

        if(action){
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                // 通过失败处理器 返回验证码是否匹配的信息
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ValidateCodeException, ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, SessionKey.IMAGE_CODE_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (codeInSession.isExpried()) {
            sessionStrategy.removeAttribute(request, SessionKey.IMAGE_CODE_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, SessionKey.IMAGE_CODE_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
