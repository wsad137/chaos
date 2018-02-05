package chaos.core.commons;

import chaos.core.service.CaptchaService_;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * ©土土网 app-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-26
 */
public class KaptchaUtils_ {

    private static KaptchaUtils_ instance;
    private ApplicationContext context;
    private CaptchaService_ captchaService;


    private static KaptchaUtils_ getInstance() {
        Assert.notNull(instance, "KaptchaHelper 还未初始化！");
        return instance;
    }

    private KaptchaUtils_(ApplicationContext context) {
        this.context = context;
        this.captchaService = this.context.getBean(CaptchaService_.class);
    }

    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
        if (instance != null) return;
        instance = new KaptchaUtils_(context);
    }

    private CaptchaService_ getCaptchaService() {
        return captchaService;
    }

    public static boolean isVerify(String iCode) {
        return getInstance().getCaptchaService().isVerify(iCode);
//        return getCaptchaService().isVerify(iCode);

    }
}
