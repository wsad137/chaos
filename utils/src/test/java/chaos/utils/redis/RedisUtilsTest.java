package chaos.utils.redis;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * RedisUtils Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>五月 9, 2018</pre>
 */
public class RedisUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: init(_RedisConfig config)
     */
    @Test
    public void testInit() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: get(String key)
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: set(String key, String val)
     */
    @Test
    public void testSetForKeyVal() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: set(String key, String val, Long seconds)
     */
    @Test
    public void testSetForKeyValSeconds() throws Exception {
//TODO: Test goes here...

        RedisUtils.set("abcd", "asdfasdf", 10L);
        System.out.println(RedisUtils.get("abcd"));
    }

    /**
     * Method: getJedis()
     */
    @Test
    public void testGetJedis() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: setJedis(Jedis jedis)
     */
    @Test
    public void testSetJedis() throws Exception {
//TODO: Test goes here...
    }

    /**
     * Method: test()
     */
    @Test
    public void testTest() throws Exception {
//TODO: Test goes here...
    }


}
