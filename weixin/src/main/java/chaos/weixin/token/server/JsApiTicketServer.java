/**
 *
 */
package chaos.weixin.token.server;

import chaos.weixin.common.Config;


/**
 * Ticket server适配器
 *
 * @author ChengNing
 * @date 2015年1月29日
 */
public class JsApiTicketServer extends AbsServer implements TicketServer {


//    private Config

    public JsApiTicketServer(Config config) {
        super(config);
    }

//    private String publicNo;

//    public JsApiTicketServer(String publicNo) {
//        this.publicNo = publicNo;
//    }

    /**
     *
     */
    public String ticket() {
        return super.token(config);
    }

//    public String ticket(String publicNo) {
//        return super.token(publicNo);
//    }

    /**
     */
    @Override
    protected String getCustomerServerClass() {
        return config.getJsApiTicketServer();
    }

//    @Override
//    public String getCustomerServerClass(String publicNo) {
//        return Config.instance(publicNo).getJsApiTicketServer();
//    }
//
//    @Override
//    public IServer defaultServer(String publicNo) {
//        return JsApiTicketMemServer.instance(publicNo);
//    }

    /**
     *
     */
    @Override
    public IServer defaultServer() {
        return JsApiTicketMemServer.instance(config);
    }


}
