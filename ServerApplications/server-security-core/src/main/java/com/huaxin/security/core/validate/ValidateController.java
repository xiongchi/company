package com.huaxin.security.core.validate;

import com.huaxin.security.core.constans.SessionKey;
import com.huaxin.security.core.validate.img.ImageCode;
import com.huaxin.security.core.validate.sms.SmsCode;
import com.huaxin.security.core.validate.sms.SmsCodeGenerator;
import com.huaxin.security.core.validate.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/15
 */
@RestController
public class ValidateController {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    @Qualifier("imageCodeGenerator")
    private ValidateGenerator imageCodeGenerator;

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SessionKey.IMAGE_CODE_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        SmsCode smsCode = smsCodeGenerator.generator(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletWebRequest(request), SessionKey.SMS_CODE_KEY, smsCode);
        String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
        smsCodeSender.send(mobile, smsCode.getCode());
    }
}
