package chaos.weixin;

import chaos.utils.object.ObjectUtils;
import chaos.weixin.common.Config;
import chaos.weixin.common.WeiXinConfig_;
import chaos.weixin.oauth.OAuthException;
import chaos.weixin.oauth.OAuthManager;
import chaos.weixin.oauth.protocol.get_access_token.GetAccessTokenRequest;
import chaos.weixin.oauth.protocol.get_access_token.GetAccessTokenResponse;
import chaos.weixin.oauth.protocol.get_userinfo.GetUserinfoRequest;
import chaos.weixin.oauth.protocol.get_userinfo.GetUserinfoResponse;
import chaos.weixin.token.timer.AccessTokenTimer;
import chaos.weixin.token.timer.JsApiTicketTimer;
import chaos.weixin.user.User;
import chaos.weixin.user.UserManager;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Timer;

/**
 * Created by chaos on 2017/4/10.
 * 作者：王健
 * qq:1413221142
 */
public class WeiXinUtils_ {

    private Timer timer;

    private static final Logger log = Logger.getLogger(WeiXinUtils_.class);

    private static WeiXinUtils_ weiXinUtils_;
    private ApplicationContext context;

    private WeiXinUtils_(ApplicationContext context) {
        this.context = context;
        log.info("accessToken监听器启动..........");
        timer = new Timer("wechat", true);

        /*注册所有公众号*/

//        WeiXinConfig_ helper = null;
//        try {
//            helper = WeiXinConfig_.getInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        for (Config config : WeiXinConfig_.getConfigs().values()) {
            //注册定时任务
            registeAccessTokenTimer(config);
            //注册jsapi_ticket定时器
            registeJsApiTicketTimer(config);
        }
    }

    private WeiXinUtils_() {
    }

    private WeiXinUtils_ getInstance() {
        if (weiXinUtils_ == null) weiXinUtils_ = new WeiXinUtils_(context);
        Assert.notNull(weiXinUtils_, "WeiXinConfig_ 还未初始化！");
        return weiXinUtils_;
    }

//    public WeChatService getWeChatService() {
//        return weChatService;
//    }
//
//    private WeChatService weChatService;


    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
//        weiXinUtils_ = new WeiXinUtils_(context);
        if (weiXinUtils_ == null) weiXinUtils_ = new WeiXinUtils_(context);
        weiXinUtils_.context = context;
//        this.context = context;


    }

//    public void init(ApplicationContext context, String publicNo) {
//        Assert.notNull(context, "ApplicationContext 不能为空！");
//        weiXinUtils_ = new WeiXinUtils_(context, publicNo);
//    }

    /**
     * 刷新token
     */
    public static void refreshToken() {
//        WeiXinConfig_.getInstance().refresh();

        /*注册所有公众号*/
        for (Config config : WeiXinConfig_.getConfigs().values()) {
            AccessTokenTimer accessTokenTimer = new AccessTokenTimer(config);
            JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer(config);
            Timer timer = new Timer();
            timer.schedule(accessTokenTimer, AccessTokenTimer.DELAY);
            timer.schedule(jsApiTicketTimer, AccessTokenTimer.DELAY);
        }

    }


    /**
     * 注册accessToken定时器
     */
    private void registeAccessTokenTimer(Config config) {
        AccessTokenTimer accessTokenTimer = new AccessTokenTimer(config);
        timer.schedule(accessTokenTimer, AccessTokenTimer.DELAY, AccessTokenTimer.PERIOD);
        log.info("accessToken定时器注册成功，执行间隔为" + AccessTokenTimer.PERIOD);
    }

    /**
     * 注册jsapi_ticket定时器
     */
    public void registeJsApiTicketTimer(Config config) {
        JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer(config);
        timer.schedule(jsApiTicketTimer, JsApiTicketTimer.DELAY, JsApiTicketTimer.PERIOD);
    }


    public static Map getUserByCode(String code, Config config) {
        GetAccessTokenRequest req = new GetAccessTokenRequest(code, config);
        GetAccessTokenResponse accessToken = null;
        Map<String, Object> toMap;
        try {
            accessToken = OAuthManager.getAccessToken(req);
            GetUserinfoRequest reqUser = new GetUserinfoRequest(accessToken.getAccess_token(), accessToken.getOpenid());
            GetUserinfoResponse userinfo = OAuthManager.getUserinfo(reqUser);
            toMap = ObjectUtils.objectToMap(userinfo);
            UserManager userManager = new UserManager(config);
            User userInfo = userManager.getUserInfo(userinfo.getOpenid());
            Map<String, Object> toMap1 = ObjectUtils.objectToMap(userInfo);
            toMap.putAll(toMap1);
        } catch (OAuthException e) {
            e.printStackTrace();
            return Maps.newHashMap();
        }
        return toMap;
    }

}
