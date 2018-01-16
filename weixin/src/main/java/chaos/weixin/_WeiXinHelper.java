package chaos.weixin;

import chaos.weixin.common.Config;
import chaos.weixin.common.ConfigHelper;
import chaos.weixin.token.timer.AccessTokenTimer;
import chaos.weixin.token.timer.JsApiTicketTimer;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.Timer;

/**
 * Created by chaos on 2017/4/10.
 * 作者：王健
 * qq:1413221142
 */
public class _WeiXinHelper {

    private Timer timer;

    private static final Logger log = Logger.getLogger(_WeiXinHelper.class);

    public _WeiXinHelper instance;

//    public static WeChatHelper getInstance() {
//        Assert.notNull(instance, "ConfigHelper 还未初始化！");
//        return instance;
//    }

//    public WeChatService getWeChatService() {
//        return weChatService;
//    }
//
//    private WeChatService weChatService;


    public void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
//        instance = new _WeiXinHelper(context);
        this.context = context;


        log.info("accessToken监听器启动..........");
        timer = new Timer("wechat", true);

        /*注册所有公众号*/

        ConfigHelper helper = null;
        try {
            helper = ConfigHelper.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Config config : helper.getConfigs().values()) {
            //注册定时任务
            registeAccessTokenTimer(config);
            //注册jsapi_ticket定时器
            registeJsApiTicketTimer(config);
        }
    }

//    public void init(ApplicationContext context, String publicNo) {
//        Assert.notNull(context, "ApplicationContext 不能为空！");
//        instance = new _WeiXinHelper(context, publicNo);
//    }

    /**
     * 刷新token
     */
    public static void refreshToken() {
//        ConfigHelper.getInstance().refresh();

        /*注册所有公众号*/
        for (Config config : ConfigHelper.getInstance().getConfigs().values()) {
            AccessTokenTimer accessTokenTimer = new AccessTokenTimer(config);
            JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer(config);
            Timer timer = new Timer();
            timer.schedule(accessTokenTimer, AccessTokenTimer.DELAY);
            timer.schedule(jsApiTicketTimer, AccessTokenTimer.DELAY);
        }

    }


    private ApplicationContext context;


//    public _WeiXinHelper() {
//    }


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
    private void registeJsApiTicketTimer(Config config) {
        JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer(config);
        timer.schedule(jsApiTicketTimer, JsApiTicketTimer.DELAY, JsApiTicketTimer.PERIOD);
    }

}
