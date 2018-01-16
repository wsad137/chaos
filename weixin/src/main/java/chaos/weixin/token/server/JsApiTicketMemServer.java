/**
 *
 */
package chaos.weixin.token.server;

import chaos.weixin.common.Config;
import chaos.weixin.token.Ticket;
import chaos.weixin.token.TicketType;

/**
 * 内存控制单例
 *
 * @author ChengNing
 * @date 2015年1月29日
 */
public class JsApiTicketMemServer implements IServer {

//    private  JsApiTicketMemServer ticketServer;

    private Ticket jsApiTicket = new Ticket(TicketType.jsapi);

    private int requestTimes = 1;//token请求失败后重新请求的次数

    private Config config;

    /**
     * 私有构造
     */
    private JsApiTicketMemServer(Config config) {
        this.config = config;
        //获取新的token
        refresh(config);
    }

//
//    /**
//     * 私有构造
//     */
//    private JsApiTicketMemServer(String publicNo) {
//        //获取新的token
//        refresh(publicNo);
//    }

    /**
     * token中控服务器实例
     *
     * @return ticket服务器实例
     */
    public static JsApiTicketMemServer instance(Config config) {
        return new JsApiTicketMemServer(config);
    }

    /**
     * token中控服务器实例
     *
     * @return ticket服务器实例
     */
//    public static JsApiTicketMemServer instance(String publicNo) {
//        return ticketServer = new JsApiTicketMemServer(publicNo);
//    }


    /**
     * 通过中控服务器得到accessToken
     *
     * @return
     */
    public String token(Config config) {
        //没有可用的token，则去刷新
        if (!this.jsApiTicket.isValid()) {
            refresh(config);
        }
        return this.jsApiTicket.getToken();
    }
//
//    public String token(String publicNo) {
//        //没有可用的token，则去刷新
//        if (!this.jsApiTicket.isValid()) {
//            refresh(publicNo);
//        }
//        return this.jsApiTicket.getToken(publicNo);
//    }

    /**
     * 服务器刷新token
     */
    private void refresh(Config config) {
        for (int i = 0; i < requestTimes; i++) {
            //请求成功则退出
            if (this.jsApiTicket.request(config))
                break;
        }
    }


//    /**
//     * 服务器刷新token
//     */
//    private void refresh(String publicNo) {
//        for (int i = 0; i < requestTimes; i++) {
//            //请求成功则退出
//            if (this.jsApiTicket.request(publicNo))
//                break;
//        }
//    }

}
