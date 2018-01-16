package chaos.weixin.oauth.protocol.get_access_token;

import chaos.weixin.common.Config;
import org.apache.commons.lang3.StringUtils;

/**
 * 请求：通过code换取网页授权access_token
 * Created by xuwen on 2015-12-11.
 */
public class GetAccessTokenRequest {

    private Config config;

//    public GetAccessTokenRequest(String code) {
//        this.code = code;
//    }

//    private String publicNo;

    public GetAccessTokenRequest(String code, Config config) {
        this.code = code;
        this.config = config;
        this.appid = config.getAppid();
        this.secret = config.getAppSecret();
    }

    private String appid;
    private String secret;
    private String code;
    private String grant_type = "authorization_code";

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
