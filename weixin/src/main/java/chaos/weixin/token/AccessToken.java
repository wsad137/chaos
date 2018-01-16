/**
 *
 */
package chaos.weixin.token;


import org.apache.log4j.Logger;
import chaos.weixin.common.Config;


/**
 * Access token实体模型
 *
 * @author ChengNing
 * @date 2014年12月12日
 */
public class AccessToken extends Token {

    private static Logger logger = Logger.getLogger(AccessToken.class);
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    //	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.controller/cgi-bin/token?grant_type=client_credential";
    @Override
    protected String tokenName() {
        return "access_token";
    }

    @Override
    protected String expiresInName() {
        return "expires_in";
    }

//    private String publicNo;

//    public AccessToken(String publicNo) {
//        this.publicNo = publicNo;
//    }

    public AccessToken() {
    }


    /**
     * 组织accesstoken的请求utl
     */
    @Override
    protected String accessTokenUrl(Config config) {
        String appid = config.getAppid();
        String appsecret = config.getAppSecret();
        String url = ACCESS_TOKEN_URL + "&appid=" + appid + "&secret=" + appsecret;
        logger.info("创建获取access_token url");
        return url;
    }



}
