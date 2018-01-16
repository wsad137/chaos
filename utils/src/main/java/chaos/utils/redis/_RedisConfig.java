package chaos.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chaos on 2018/1/3.
 * 作者：王健
 * qq:1413221142
 */
public class _RedisConfig {
    private final static Logger log = LoggerFactory.getLogger(_RedisConfig.class);
    /**
     * 主机地址 默认localhost
     */
    private String hostName = "localhost";
    /**
     * 端口
     */
    private String port = "6379";
    /**
     * 密码
     */
    private String password;
    /**
     * 过期时间
     */
    private String expire = "2000";
    /**
     * 超时时间
     */
    private String timeout = "2000";
    /**
     * 最大链接数
     */
    private String maxTotal = "30";
    /**
     * 最大空闲时间
     */
    private String maxIdle = "10";
    /**
     * 最大链接数
     */
    private String numTestsPerEvictionRun = "1024";
    /**
     * 释放扫描的扫描间隔
     */
    private String timeBetweenEvictionRunsMillis = "30000";
    /**
     * 连接的最小空闲时间
     */
    private String minEvictableIdleTimeMillis = "1800000";
    /**
     * 连接控歘按时间多久后释放，当空闲时间>该值且空闲连接>最大空闲连接数时直接释放
     */
    private String softMinEvictableIdleTimeMillis = "1000";
    /**
     * 获得链接时的最大等待毫秒数，小于0：阻塞不确定时间，默认-1
     */
    private String maxWaitMillis = "1500";
    /**
     * 在获得链接的时候检查有效性，默认false
     */
    private String testOnBorrow = "false";
    /**
     * 在空闲时检查有效性，默认false
     */
    private String testWhileIdle = "false";
    /**
     * 连接耗尽时是否阻塞，false报异常，true阻塞超时,默认true
     */
    private String blockWhenExhausted;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(String maxTotal) {
        this.maxTotal = maxTotal;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(String numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public String getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(String timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(String minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public String getSoftMinEvictableIdleTimeMillis() {
        return softMinEvictableIdleTimeMillis;
    }

    public void setSoftMinEvictableIdleTimeMillis(String softMinEvictableIdleTimeMillis) {
        this.softMinEvictableIdleTimeMillis = softMinEvictableIdleTimeMillis;
    }

    public String getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(String maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(String testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public String getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(String testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public String getBlockWhenExhausted() {
        return blockWhenExhausted;
    }

    public void setBlockWhenExhausted(String blockWhenExhausted) {
        this.blockWhenExhausted = blockWhenExhausted;
    }
}
