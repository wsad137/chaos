/**
 *
 */
package chaos.weixin.token.timer;

import chaos.weixin.common.Config;
import chaos.weixin.token.AccessToken;
import chaos.weixin.token.server.AccessTokenServer;
import chaos.weixin.token.server._CustomerServer;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * access token 定时器
 *
 * @author ChengNing
 * @date 2015年1月8日
 */
public class AccessTokenTimer extends TimerTask {

    private static Logger logger = Logger.getLogger(AccessTokenTimer.class);

    //accessToken有效期7200秒,提前200秒请求新的token
    public static final long PERIOD = 7000 * 1000;
    public static final long DELAY = 0; //此任务的延迟时间为0，即立即执行

//    private String publicNo;

//    public AccessTokenTimer(String publicNo) {
//        this.publicNo = publicNo;
//    }

    private AccessTokenTimer() {

    }

    private Config config;

    public AccessTokenTimer(Config config) {
        this.config = config;
    }

    @Override
    public void run() {
        logger.info("accessToken 定时任务启动，获取新的accessToken");
        //得到新的access token
        AccessToken accessToken = new AccessToken();
//        boolean empty = StringUtils.isEmpty(publicNo);
//        if (!empty) accessToken = new AccessToken(publicNo);
        //获取成功之后持久化accessToken
        boolean request;
//        if (empty) {
        request = accessToken.request(config);
//        } else {
//            request = accessToken.request(publicNo);
//        }
        if (request) {
            AccessTokenServer accessTokenServer = new AccessTokenServer(config);
//            if (!empty) accessTokenServer = new AccessTokenServer(publicNo);
            _CustomerServer customerServer = (_CustomerServer) accessTokenServer.customerServer();
//            customerServer.setPublicNo(publicNo);
//            customerServer.save(accessToken, publicNo);
            customerServer.save(config, accessToken);
        }
    }

}
