/**
 *
 */
package chaos.weixin.token.server;

import chaos.weixin.common.Config;

/**
 * 适配器
 *
 * @author ChengNing
 * @date 2015年1月30日
 */
public class AccessTokenServer extends AbsServer implements TokenServer {

//    private String publicNo;

//    public AccessTokenServer(String publicNo) {
//        this.publicNo = publicNo;
//    }

    private AccessTokenServer() {
        super(null);
    }

//    private Config config;

    public AccessTokenServer(Config config) {
        super(config);
//        this.config = config;

    }

    /**
     *
     */
    public String token() {
        return super.token(config);
    }

    /**
     */
//    public String token(String publicNo) {
//        return super.token(publicNo);
//    }
    @Override
    protected String getCustomerServerClass() {
        return config.getAccessTokenServer();
    }

    @Override
    public IServer defaultServer() {
        return AccessTokenMemServer.instance(config);
    }

//    @Override
//    public String getCustomerServerClass(String publicNo) {
//        return Config.instance(publicNo).getAccessTokenServer();
//    }

//    @Override
//    public IServer defaultServer(String publicNo) {
//        return AccessTokenMemServer.instance(publicNo);
//    }

}
