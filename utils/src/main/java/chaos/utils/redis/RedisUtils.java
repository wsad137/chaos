package chaos.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by chaos on 2018/1/3.
 * 作者：王健
 * qq:1413221142
 */
public class RedisUtils {
    private final static Logger log = LoggerFactory.getLogger(RedisUtils.class);
    public static _RedisConfig _config = new _RedisConfig();

    private static Jedis jedis;

    public static void init(_RedisConfig config) {
        _config = config;
    }

    public static String get(String key) {
        return getJedis().get(key);
    }

    public static void set(String key, String val) {
        getJedis().set(key, val);
    }

    public static Jedis getJedis() {
        if (jedis == null) jedis = new Jedis(_config.getHostName());
        return jedis;
    }

    public static void setJedis(Jedis jedis) {
        RedisUtils.jedis = jedis;
    }
}
