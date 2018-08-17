package com.huaxin.security.core.validate.img;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author: XiongChi
 * @Description:
 * @Date: 2018/8/15
 */
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    // 是否过期
    public boolean isExpried(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
