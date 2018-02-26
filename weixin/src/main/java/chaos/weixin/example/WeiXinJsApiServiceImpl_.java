package chaos.weixin.example;

import chaos.utils.redis.RedisUtils;
import chaos.weixin.common.Config;
import chaos.weixin.token.Token;
import chaos.weixin.token.server._CustomerServer;
import org.springframework.stereotype.Service;

/**
 * ©土土网 app-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-11-01
 */
@Service
public class WeiXinJsApiServiceImpl_ extends _CustomerServer {

    private static String key = "chaos-weixin-jsApiAccesstoken";

    @Override
    public boolean save(Config config, Token token) {
        RedisUtils.set(key + "-" + config.getAppid(), token.getToken());
        return false;
    }

    @Override
    protected String find(Config config) {
        return RedisUtils.get(key + "-" + config.getAppid());
    }

}
