package chaos.weixin.common;

import chaos.utils.object.ObjectUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

/**
 * Created by chaos on 2017/12/21.
 * 作者：王健
 * qq:1413221142
 */
public class WeiXinConfig_ {

    private final static Logger log = LoggerFactory.getLogger(WeiXinConfig_.class);

    private static final String configFile = "/chaos-weixin.properties";

//    private List<Config> configs = Lists.newArrayList();

    public static Map<String, Config> getConfigs() {
        return getInstance().configs;
    }

    public void setConfigs(Map<String, Config> configs) {
        this.configs = configs;
    }

    private Map<String, Config> configs = Maps.newHashMap();

    public static Config getConfigDef() {
        return getInstance().configDef;
    }

    private Config configDef;

    private Properties p = new Properties();

    private static WeiXinConfig_ weiXinConfig_;

    private WeiXinConfig_() {
        init();
    }

    private static WeiXinConfig_ getInstance() {
        if (weiXinConfig_ == null) weiXinConfig_ = new WeiXinConfig_();
        return weiXinConfig_;
    }

    private void refresh() {
        configs.clear();
        init();
    }

    private void init() {
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream(configFile);
            if (in == null) {
                log.error("根目录下找不到wechat4j.properties文件");
                return;
            }
            p.load(in);
            Enumeration<?> names = p.propertyNames();
            while (names.hasMoreElements()) {
                String o = ObjectUtils.toString(names.nextElement());
                for (int i = 1; i < 20; i++) {
                    if (!o.contains("item" + i)) continue;
                    Config config = toModle(i);
                    if (StringUtils.isEmpty(config.getAppid())) continue;
                    if (configDef == null) configDef = config;
                    configs.put(config.getAppid(), config);
                }

            }

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

        } catch (Exception e) {
            log.error("load wechat4j.properties error,class根目录下找不到wechat4j.properties文件");
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ignored) {
            }
        }
        log.info("load wechat4j.properties success");
    }

    private Config toModle(int i) {
        Config config = new Config();
        try {
            config.setUrl(p.getProperty("item" + i + ".wechat.url"));
//        this.url = p.getProperty("wechat.url");
//        if (StringUtils.isNotBlank(url)) this.url = this.url.trim();
            config.setEncodingAESKey(p.getProperty("item" + i + ".wechat.encodingaeskey"));
//        this.encodingAESKey = p.getProperty("wechat.encodingaeskey");
//        if (StringUtils.isNotBlank(encodingAESKey)) this.encodingAESKey = this.encodingAESKey.trim();
            config.setToken(p.getProperty("item" + i + ".wechat.token"));
//        this.token = p.getProperty("wechat.token");
//        if (StringUtils.isNotBlank(token)) this.token = this.token.trim();
            config.setAppid(p.getProperty("item" + i + ".wechat.appid"));
//        this.appid = p.getProperty("wechat.appid");
//        if (StringUtils.isNotBlank(appid)) this.appid = this.appid.trim();
            config.setAppSecret(p.getProperty("item" + i + ".wechat.appsecret"));
//        this.appSecret = p.getProperty("wechat.appsecret");
//        if (StringUtils.isNotBlank(appSecret)) this.appSecret = this.appSecret.trim();
            config.setMchId(p.getProperty("item" + i + ".wechat.mch.id"));
//        this.mchId = p.getProperty("wechat.mch.id");
//        if (StringUtils.isNotBlank(mchId)) this.mchId = this.mchId.trim();
            config.setMchKey(p.getProperty("item" + i + ".wechat.mch.key"));
//        this.mchKey = p.getProperty("wechat.mch.key");
//        if (StringUtils.isNotBlank(mchKey)) this.mchKey = this.mchKey.trim();
            config.setAccessTokenServer(p.getProperty("wechat.accessToken.server.class"));
            config.setJsApiTicketServer(p.getProperty("wechat.ticket.jsapi.server.class"));
//        this.accessTokenServer = p.getProperty("wechat.accessToken.server.class");
//        if (StringUtils.isNotBlank(accessTokenServer)) this.accessTokenServer = this.accessTokenServer.trim();
//        this.jsApiTicketServer = p.getProperty("wechat.ticket.jsapi.server.class");
//        if (StringUtils.isNotBlank(jsApiTicketServer)) this.jsApiTicketServer = this.jsApiTicketServer.trim();
        } catch (Exception e) {
            log.warn("读取配置文件异常！");
        }
        return config;
    }


}
