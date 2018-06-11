package chaos.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.time.Instant;

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
        try {
            return getJedis().get(key);
        } catch (Exception e) {
            log.warn("获取失败！", e);
        }
        return "";
    }

    public static void set(String key, String val) {
        try {
            getJedis().set(key, val);
        } catch (Exception e) {
            log.warn("保存失败！", e);
        }
    }

    /**
     * @param key
     * @param val
     * @param seconds 过期时间秒
     */
    public static void set(String key, String val, Long seconds) {
        try {
            if (getJedis().exists(key)) {
                getJedis().set(key, val, "XX", "EX", seconds);
            } else {
                getJedis().set(key, val, "NX", "EX", seconds);
            }
        } catch (Exception e) {
            log.warn("保存失败！", e);
        }
    }

    public static Jedis getJedis() {
        if (jedis == null) jedis = new Jedis(_config.getHostName());
        return jedis;
    }

    public static void setJedis(Jedis jedis) {
        RedisUtils.jedis = jedis;
    }


    public static boolean test() {
        try {
            String val = "test..";
            set("test", val, 5L);
            String test = get("test");
            if (test.equals(val)) return true;
        } catch (Exception e) {
            log.warn("redis不可用！");
            return false;
        }
        return false;
    }
}
