package chaos.core.service.impl;

import chaos.core.commons.RequestUtils_;
import chaos.core.service.CaptchaService_;
import chaos.utils.object.ObjectUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.stereotype.Service;

import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * Created by chaos on 2018/1/19.
 * 作者：王健
 * qq:1413221142
 */
@Service
public class CaptchaService_Impl implements CaptchaService_ {
    private final static Logger log = LoggerFactory.getLogger(CaptchaService_Impl.class);

    @Autowired
    Producer captchaProducer;

    @Override
    public String getICode() {
        String capText = encodeBase64String(getCapText(RequestUtils_.getRequest()));
        return capText;
    }

    @Override
    public byte[] getICodeImg() {
        return getCapText(RequestUtils_.getRequest());
    }

    @Override
    public boolean isVerify(String str) {
        if (ObjectUtils.isEmpty(str)) return false;

        String code = (String) RequestUtils_.getRequest().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (ObjectUtils.isEmpty(code)) return false;
        if (code.equals(str)) return true;
        return false;
    }

    @Override
    public void clear() {
        RequestUtils_.getRequest().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, "");
    }

    @Override
    public void getICodeImg(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("******************验证码是: " + code + "******************");

        response.setDateHeader("Expires", 0);

        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");

        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        try {
            BufferedImage bi = ImageIO.read(new ByteArrayInputStream(getICodeImg()));
            ServletOutputStream out = null;
            out = response.getOutputStream();
            ImageIO.write(bi, "png", out);
            try {
                out.flush();
            } finally {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] getCapText(HttpServletRequest request) {
        byte[] bytes = new byte[0];
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ByteArrayOutputStream baos = null;
        baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (Exception e) {
                System.out.println("关闭文件流发生异常: " + e);
            }
        }
        return bytes;
    }
}
