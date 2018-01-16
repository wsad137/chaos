/**
 *
 */
package chaos.weixin.token.timer;

import chaos.weixin.common.Config;
import chaos.weixin.token.Ticket;
import chaos.weixin.token.TicketType;
import chaos.weixin.token.server._CustomerServer;
import chaos.weixin.token.server.JsApiTicketServer;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * @author ChengNing
 * @date 2015年1月29日
 */
public class JsApiTicketTimer extends TimerTask {

    private static Logger logger = Logger.getLogger(JsApiTicketTimer.class);
    // jsapi_ticket有效期7200秒,提前200秒请求新的token
    public static final long PERIOD = 7000 * 1000;
    public static final long DELAY = 0; // 此任务的延迟时间为0，即立即执行

//    private String publicNo;

//    public JsApiTicketTimer(String publicNo) {
//        this.publicNo = publicNo;
//    }

    private JsApiTicketTimer() {
    }

    private Config config;

    public JsApiTicketTimer(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        logger.info("jsapi_ticket 定时任务启动，获取新的jsapi_ticket");
        // 得到新的access token
        Ticket jsapiTicket = new Ticket(TicketType.jsapi);
        // 手动获取成功之后持久化accessToken

        boolean request;
//        boolean empty = StringUtils.isEmpty(publicNo);
//        if (empty) {
        request = jsapiTicket.request(config);
//        } else {
//            request = jsapiTicket.request(publicNo);
//        }

        if (request) {
            JsApiTicketServer jsapiTicketServer = new JsApiTicketServer(config);
//            if (!empty) jsapiTicketServer = new JsApiTicketServer(publicNo);
            _CustomerServer customerServer = (_CustomerServer) jsapiTicketServer.customerServer();
//            customerServer.setPublicNo(publicNo);
//            customerServer.save(jsapiTicket, publicNo);
            customerServer.save(config, jsapiTicket);
        }
    }

}
