/**
 *
 */
package chaos.weixin.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 微信配置文件 appid 为主键
 *
 * @author ChengNing
 * @date 2014年12月8日
 */
public class Config {

    private static Logger logger = Logger.getLogger(Config.class);

//    private static final String configFile = "/wechat4j.properties";

    public void setUrl(String url) {
        this.url = url;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public void setAccessTokenServer(String accessTokenServer) {
        this.accessTokenServer = accessTokenServer;
    }

    public void setJsApiTicketServer(String jsApiTicketServer) {
        this.jsApiTicketServer = jsApiTicketServer;
    }

    private String url;
    private String token;
    private String encodingAESKey;
    private String appid;
    private String appSecret;
    private String mchId;
    private String mchKey;
    private String accessTokenServer;
    private String jsApiTicketServer;
//    private static Config config = new Config();

//    public Config() {
//        Properties p = new Properties();
//        InputStream inStream = this.getClass().getResourceAsStream(configFile);
//        if (inStream == null) {
//            logger.error("根目录下找不到wechat4j.properties文件");
//            return;
//        }
//        try {
//            p.load(inStream);
//            this.url = p.getProperty("wechat.url");
//            if (StringUtils.isNotBlank(url)) this.url = this.url.trim();
//            this.encodingAESKey = p.getProperty("wechat.encodingaeskey");
//            if (StringUtils.isNotBlank(encodingAESKey)) this.encodingAESKey = this.encodingAESKey.trim();
//            this.token = p.getProperty("wechat.token");
//            if (StringUtils.isNotBlank(token)) this.token = this.token.trim();
//            this.appid = p.getProperty("wechat.appid");
//            if (StringUtils.isNotBlank(appid)) this.appid = this.appid.trim();
//            this.appSecret = p.getProperty("wechat.appsecret");
//            if (StringUtils.isNotBlank(appSecret)) this.appSecret = this.appSecret.trim();
//            this.mchId = p.getProperty("wechat.mch.id");
//            if (StringUtils.isNotBlank(mchId)) this.mchId = this.mchId.trim();
//            this.mchKey = p.getProperty("wechat.mch.key");
//            if (StringUtils.isNotBlank(mchKey)) this.mchKey = this.mchKey.trim();
//            this.accessTokenServer = p.getProperty("wechat.accessToken.server.class");
//            if (StringUtils.isNotBlank(accessTokenServer)) this.accessTokenServer = this.accessTokenServer.trim();
//            this.jsApiTicketServer = p.getProperty("wechat.ticket.jsapi.server.class");
//            if (StringUtils.isNotBlank(jsApiTicketServer)) this.jsApiTicketServer = this.jsApiTicketServer.trim();
//            inStream.close();
//        } catch (IOException e) {
//            logger.error("load wechat4j.properties error,class根目录下找不到wechat4j.properties文件");
//            e.printStackTrace();
//        }
//        logger.info("load wechat4j.properties success");
//    }

//    private Config(String publicNo) {
//
//        Properties p = new Properties();
//        InputStream inStream = this.getClass().getResourceAsStream(configFile);
//        if (inStream == null) {
//            logger.error("根目录下找不到wechat4j.properties文件");
//            return;
//        }
//        try {
//            p.load(inStream);
//            this.url = p.getProperty(publicNo + ".wechat.url");
//            if (StringUtils.isNotBlank(url)) this.url = this.url.trim();
//            this.encodingAESKey = p.getProperty(publicNo + ".wechat.encodingaeskey");
//            if (StringUtils.isNotBlank(encodingAESKey)) this.encodingAESKey = this.encodingAESKey.trim();
//            this.token = p.getProperty(publicNo + ".wechat.token");
//            if (StringUtils.isNotBlank(token)) this.token = this.token.trim();
//            this.appid = p.getProperty(publicNo + ".wechat.appid");
//            if (StringUtils.isNotBlank(appid)) this.appid = this.appid.trim();
//            this.appSecret = p.getProperty(publicNo + ".wechat.appsecret");
//            if (StringUtils.isNotBlank(appSecret)) this.appSecret = this.appSecret.trim();
//            this.mchId = p.getProperty(publicNo + ".wechat.mch.id");
//            if (StringUtils.isNotBlank(mchId)) this.mchId = this.mchId.trim();
//            this.mchKey = p.getProperty(publicNo + ".wechat.mch.key");
//            if (StringUtils.isNotBlank(mchKey)) this.mchKey = this.mchKey.trim();
//            this.accessTokenServer = p.getProperty(publicNo + ".wechat.accessToken.server.class");
//            if (StringUtils.isNotBlank(accessTokenServer)) this.accessTokenServer = this.accessTokenServer.trim();
//            this.jsApiTicketServer = p.getProperty(publicNo + ".wechat.ticket.jsapi.server.class");
//            if (StringUtils.isNotBlank(jsApiTicketServer)) this.jsApiTicketServer = this.jsApiTicketServer.trim();
//            inStream.close();
//        } catch (IOException e) {
//            logger.error("load wechat4j.properties error,class根目录下找不到wechat4j.properties文件");
//            e.printStackTrace();
//        }
//        logger.info("load wechat4j.properties success");
//
//
//    }

    /**
     * 刷新动态配置
     */
//    public static void refresh() {
//        config = new Config();
//    }

//    public static void refresh(String publicNo) {
//        config = new Config(publicNo);
//    }

//    public static Config instance() {
//        return config = new Config();
//    }

//    public static Config instance(String publicNo) {
//        return config = new Config(publicNo);
//    }
    public String getToken() {
        return token;
    }

    public String getAppid() {
        return appid;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public String getUrl() {
        return url;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public String getAccessTokenServer() {
        return accessTokenServer;
    }

    public String getJsApiTicketServer() {
        return jsApiTicketServer;
    }


}
