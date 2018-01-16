/**
 *
 */
package chaos.weixin.token.server;

import chaos.weixin.common.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;

/**
 * @author ChengNing
 * @date 2015年1月29日
 */
public abstract class AbsServer implements IServer {

    private static Logger logger = Logger.getLogger(AbsServer.class);

    protected Config config;

    protected String customerServerClass;

    public AbsServer(Config config) {
        this.config = config;
        this.customerServerClass = getCustomerServerClass();
    }

    private AbsServer() {

    }


    @Override
    public String token(Config config) {
        return server().token(config);
    }

//	@Override
//	public String token(String publicNo){
//		return server().token(publicNo);
//	}

    /**
     * 得到系统可用的中控服务器
     *
     * @return 正在使用的中控服务器
     */
    public IServer server() {
        if (isCustomer())
            return customerServer();
        return defaultServer();
    }

    /**
     * 加载自定义中控服务器
     *
     * @return 自定义的中控服务器
     */
    public IServer customerServer() {
        String className = customerServerClass;
        IServer customerServer = null;
        try {
            Class clazz = Class.forName(className);
//            customerServer = (IServer) clazz.newInstance(config);
//            Constructor constructor = clazz.getConstructor(Config.class);
            Constructor constructor = clazz.getConstructor();
//            customerServer = (IServer) constructor.newInstance(config);
            customerServer = (IServer) clazz.newInstance();
//            customerServer.token(config);
        } catch (Exception e) {
            logger.error("系统找不到" + className);
            logger.error("自定义server实例化失败，" + e.getMessage());
            e.printStackTrace();
        }
        return customerServer;
    }

    /**
     * 如果配置文件中配置了AccessTokenServer，那么使用客户自定义server
     *
     * @return 是否配置了自定义中控服务器
     */
    public boolean isCustomer() {
        if (StringUtils.isBlank(customerServerClass))
            return false;
        return true;
    }

    /**
     * 指定的默认中控服务器
     *
     * @return 默认的中控服务器
     */
    public abstract IServer defaultServer();

    /**
     * 自定义服务器的类
     *
     * @return
     */
    protected abstract String getCustomerServerClass();

//	public abstract String getCustomerServerClass(String publicNo);
//
//	public abstract IServer defaultServer(String publicNo);
}
