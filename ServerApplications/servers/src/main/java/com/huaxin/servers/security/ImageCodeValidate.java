package com.huaxin.servers.security;

import com.huaxin.security.core.validate.img.ImageCode;
import com.huaxin.security.core.validate.ValidateGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XiongChi
 * @Description: 自定义图片验证码
 * @Date: 2018/8/16
 */
//@Component("imageCodeGenerator")
public class ImageCodeValidate implements ValidateGenerator {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public ImageCode generator(ServletWebRequest request) {
        log.info("自定义图形验证码");
        return null;
    }
}
