package chaos.weixin.menu;


import chaos.weixin.common.Config;
import chaos.weixin.exception.WeChatException;
import chaos.weixin.token.TokenProxy;
import chaos.weixin.util.WeChatUtil;
import chaos.weixin.util.lang.HttpUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 微信菜单操作
 *
 * @author Zhangxs
 * @version 2015-7-4
 */
public class MenuManager {
    private Config config;


    private MenuManager() {

    }

    private MenuManager(Config config) {
        this.config = config;
        this.accessToken = TokenProxy.accessToken(config);
    }

    public static MenuManager getInstance(Config config) {
        return new MenuManager(config);
    }

    private static Logger logger = Logger.getLogger(MenuManager.class);

    private static final String MENU_CREATE_POST_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
    private static final String MENU_GET_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    private static final String MENU_DEL_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

    private String accessToken;

//    @Deprecated
//    public MenuManager() {
//
//        this.accessToken = TokenProxy.accessToken(config);
//    }

//    public MenuManager(String publicNo) {
//        this.accessToken = TokenProxy.accessToken(publicNo);
//    }

    /**
     * 创建菜单
     *
     * @throws WeChatException
     */
    public void create(Menu menu) throws WeChatException {
        logger.info("创建菜单");
        String resultStr = HttpUtils.post(MENU_CREATE_POST_URL + this.accessToken, JSON.toJSONString(menu));
        WeChatUtil.isSuccess(resultStr);
    }

    /**
     * 查询菜单
     */
    public Menu getMenu() {
        logger.info("查询菜单");
        String resultStr = HttpUtils.get(MENU_GET_GET_URL + this.accessToken);
        try {
            WeChatUtil.isSuccess(resultStr);
        } catch (WeChatException e) {
            e.printStackTrace();
            return null;
        }
        JSONObject menuObject = JSONObject.parseObject(resultStr);
        Menu menu = menuObject.getObject("menu", Menu.class);
        return menu;
    }

    /**
     * 删除菜单
     *
     * @throws WeChatException
     */
    public void delete() throws WeChatException {
        logger.info("删除菜单");
        String resultStr = HttpUtils.get(MENU_DEL_GET_URL + this.accessToken);
        WeChatUtil.isSuccess(resultStr);
    }

}
