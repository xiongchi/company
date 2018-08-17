package com.huaxin.security.core.validate;

import com.huaxin.security.core.validate.img.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/15
 */
public interface ValidateGenerator {
    ImageCode generator(ServletWebRequest request);
}
