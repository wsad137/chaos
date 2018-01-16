/**
 *
 */
package chaos.weixin.token.server;


import chaos.weixin.common.Config;

/**
 * @author ChengNing
 * @date 2015年1月7日
 */
public interface IServer {

    String token(Config config);
}
