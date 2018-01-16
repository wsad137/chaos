/**
 *
 */
package chaos.weixin.token;

import chaos.weixin.common.Config;
import chaos.weixin.token.server.AccessTokenServer;
import chaos.weixin.token.server.JsApiTicketServer;
import chaos.weixin.token.server.TicketServer;
import chaos.weixin.token.server.TokenServer;


/**
 * AccessToken代理
 * 所有获取accessToken的地方都通过此代理获得
 * 获得方法String token = AccessTokenProxy.token()
 *
 * @author ChengNing
 * @date 2015年1月9日
 */
public class TokenProxy {

    /**
     * 通过代理得到accessToken的串
     */
    public static String accessToken(Config config) {
        TokenServer accessTokenServer = new AccessTokenServer(config);
        return accessTokenServer.token();
    }

    /**
     * 通过代理得到jsapi_ticket
     */
    public static String jsApiTicket(Config config) {
        TicketServer ticketServer = new JsApiTicketServer(config);
        return ticketServer.ticket();
    }

//    /**
//     * 通过代理得到accessToken的串
//     */
//    public static String accessToken(String publicNo) {
//        TokenServer accessTokenServer = new AccessTokenServer(publicNo);
//        return accessTokenServer.token(publicNo);
//    }
//
//    /**
//     * 通过代理得到jsapi_ticket
//     */
//    public static String jsApiTicket(String publicNo) {
//        TicketServer ticketServer = new JsApiTicketServer(publicNo);
//        return ticketServer.ticket(publicNo);
//    }


}
