package chaos.utils.redis;

import org.junit.Test;

import java.time.Instant;

import static org.junit.Assert.*;

/**
 * Created by chaos on 2018/1/8.
 * 作者：王健
 * qq:1413221142
 */
public class RedisUtilsTest {
    @Test
    public void get() throws Exception {

    }

    @Test
    public void set() throws Exception {
        RedisUtils.set("abc", Instant.now() + "");
        System.out.println(RedisUtils.get("abc"));
        assertTrue(RedisUtils.getJedis().exists("abc"));

    }

}