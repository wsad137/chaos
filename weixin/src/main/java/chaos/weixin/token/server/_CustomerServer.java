/**
 *
 */
package chaos.weixin.token.server;


import chaos.weixin.common.Config;
import chaos.weixin.token.Token;

/**
 * @author ChengNing
 * @date 2015年1月30日
 */
public abstract class _CustomerServer implements IServer {

    private Config config;

//    public String token(Config config) {
//        this.config = config;
//        return find(config);
//    }


//    public String token(String publicNo) {
//        return find(publicNo);
//    }

    public _CustomerServer(Config config) {
        this.config = config;
    }

    @Override
    public String token(Config config) {
        this.config = config;
        return find(config);
    }

    public _CustomerServer() {
    }

//    public void setPublicNo(String publicNo) {
//        this.publicNo = publicNo;
//    }

//    private String publicNo;

//    public CustomerServer(String publicNo) {
//        this.publicNo = publicNo;
//    }

    /**
     * 保存或者更新accesstoken到数据库
     * 由客户自己实现数据库插入或者更新操作
     *
     * @param token 得到的token或者ticket，需要保存
     * @return
     */
//    public abstract boolean save(Token token, String publicNO);
    public abstract boolean save(Config config, Token token);

    /**
     * 从数据库得到accessToken
     * 由客户自己实现数据库的查询操作
     *
     * @return
     */
//    protected abstract String find(String publicNo);
    protected abstract String find(Config config);

}
