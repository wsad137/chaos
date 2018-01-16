/**
 *
 */
package chaos.weixin.token;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import chaos.weixin.common.Config;
import org.apache.log4j.Logger;
import chaos.weixin.token.timer.AccessTokenTimer;
import chaos.weixin.token.timer.JsApiTicketTimer;


/**
 * Access Token 监听器
 *
 * @author ChengNing
 * @date 2015年1月8日
 */
public class TokenListener implements ServletContextListener {

    private static Logger log = Logger.getLogger(TokenListener.class);

    private Timer timer = null;

    private TokenListener() {

    }

    private Config config;

    public TokenListener(Config config) {
        this.config = config;
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        log.info("accessToken监听器启动..........");
        timer = new Timer(true);
        //注册定时任务
        registeAccessTokenTimer(config);
        //注册jsapi_ticket定时器
        registeJsApiTicketTimer(config);
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        timer.cancel();
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
    private void registeJsApiTicketTimer(Config config) {
        JsApiTicketTimer jsApiTicketTimer = new JsApiTicketTimer(config);
        timer.schedule(jsApiTicketTimer, JsApiTicketTimer.DELAY, JsApiTicketTimer.PERIOD);
    }

}
