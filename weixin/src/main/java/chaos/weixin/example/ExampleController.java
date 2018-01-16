package chaos.weixin.example;

import chaos.weixin._WeixinController;
import chaos.weixin.common.Config;
import chaos.weixin.common.ConfigHelper;
import chaos.weixin.request.WechatRequest;
import chaos.weixin.response.WechatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chaos on 2018/1/3.
 * 作者：王健
 * qq:1413221142
 */
@RequestMapping("/weixin")
@Controller
public class ExampleController extends _WeixinController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;
    private Logger log = LoggerFactory.getLogger(ExampleController.class);

    //    JspApplicationContextImpl
    @RequestMapping(value = "/main")
    @ResponseBody
    public void test() {
        Config config = ConfigHelper.getInstance().getConfigs().get("wx139feb09a227b2ee");
        init(request, config);
        try {
            String res = execute();
            response.getOutputStream().write(res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("-=-=-=-=-" + request.getRemoteAddr());
//        System.out.println("Test................");
//        String a = null;
//        a.toString();
//        throw new IllegalArgumentException("唯一约束异常！");
//        return CaseRes.New();
    }

    @Override
    protected void onText(String content) {
        log.warn(content);
    }

    @Override
    protected void onImage() {

    }

    @Override
    protected void onVoice() {

    }

    @Override
    protected void onVideo() {

    }

    @Override
    protected void onShortVideo() {

    }

    @Override
    protected void onLocation(WechatRequest wechatRequest, WechatResponse wechatResponse) {

    }

    @Override
    protected void onLink(WechatRequest wechatRequest, WechatResponse wechatResponse) {

    }

    @Override
    protected void onUnknown() {

    }

    @Override
    protected void click() {

    }

    @Override
    protected void subscribe() {

    }

    @Override
    protected void unSubscribe() {

    }

    @Override
    protected void scan() {

    }

    @Override
    protected void location() {

    }

    @Override
    protected void view() {

    }

    @Override
    protected void templateMsgCallback() {

    }

    @Override
    protected void scanCodePush() {

    }

    @Override
    protected void scanCodeWaitMsg() {

    }

    @Override
    protected void picSysPhoto() {

    }

    @Override
    protected void picPhotoOrAlbum() {

    }

    @Override
    protected void picWeixin() {

    }

    @Override
    protected void locationSelect() {

    }

    @Override
    protected void kfCreateSession() {

    }

    @Override
    protected void kfCloseSession() {

    }

    @Override
    protected void kfSwitchSession() {

    }

    @Override
    protected void dispatchDiy() {

    }
}
