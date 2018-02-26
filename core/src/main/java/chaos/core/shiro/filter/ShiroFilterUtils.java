package chaos.core.shiro.filter;

import chaos.utils.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 开发公司：itboy.net<br/>
 * 版权：itboy.net<br/>
 * <p>
 * <p>
 * Shiro Filter 工具类
 * <p>
 * <p>
 * <p>
 * 区分　责任人　日期　　　　说明<br/>
 * 创建　周柏成　2016年5月27日 　<br/>
 * <p>
 * *******
 * <p>
 *
 * @author zhou-baicheng
 * @version 1.0, 2016年5月27日 <br/>
 * @email i@itboy.net
 */
public class ShiroFilterUtils {
    final static Class<? extends ShiroFilterUtils> CLAZZ = ShiroFilterUtils.class;
    private static Logger log = LoggerFactory.getLogger(PermissionFilter.class);
    //登录页面
    static final String LOGIN_URL = "/u/login.shtml";
    //踢出登录提示
    final static String KICKED_OUT = "/open/kickedOut.shtml";
    //没有权限提醒
    final static String UNAUTHORIZED = "/open/unauthorized.shtml";

    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(ServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With"));
    }

    /**
     * response 输出JSON
     *
     * @param response
     * @param resultMap
     * @throws IOException
     */
    public static void out(ServletResponse response, Object resultMap) {

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.println(JsonUtils.tojSON(resultMap));
//			out.println(JSONObject.fromObject(resultMap).toString());
        } catch (Exception e) {
//			LoggerUtils.fmtError(CLAZZ, e, "输出JSON报错。");
            log.error("输出JSON报错。");
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }
}