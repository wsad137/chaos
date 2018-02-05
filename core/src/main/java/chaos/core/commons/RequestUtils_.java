package chaos.core.commons;

import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ©chaos
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-03-23
 */
public class RequestUtils_ {

    static final Logger log = Logger.getLogger(RequestUtils_.class);

    private ApplicationContext context;

    private static RequestUtils_ requestHelper = null;

    private RequestUtils_(ApplicationContext context) {
        this.context = context;
    }

    private static RequestUtils_ getInstance() {
        Assert.notNull(requestHelper, "RequestHelper 请先初始化....");
        return requestHelper;
    }

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public static RequestUtils_ init(ApplicationContext context) {
        if (requestHelper == null) requestHelper = new RequestUtils_(context);
        return requestHelper;
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = null;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (Exception e) {
            log.warn("会话已结束！");
        }
        return request;
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = null;
        try {
            response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        } catch (Exception e) {
            log.warn("会话已结束！");
        }
        return response;
    }


    public static String getIp() {
        HttpServletRequest request = getRequest();
        if (request == null) return "";

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("PRoxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

//    public static String getIp_(HttpServletRequest request) {
//        String ip = request.getHeader("X-Forwarded-For");
//        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
//            //多次反向代理后会有多个ip值，第一个ip才是真实ip
//            int index = ip.indexOf(",");
//            if (index != -1) {
//                return ip.substring(0, index);
//            } else {
//                return ip;
//            }
//        }
//        ip = request.getHeader("X-Real-IP");
//        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
//            return ip;
//        }
//        return request.getRemoteAddr();
//    }

    /**
     * 是否移动设备
     *
     * @return
     */
    public static boolean isMobile() {
        if (getRequest() == null) return false;
        UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("user-agent"));
        if (userAgent.getOperatingSystem().getDeviceType().equals(DeviceType.MOBILE)) return true;
        return false;
    }

    /**
     * 获取设备信息
     *
     * @return
     */
    public static String getDevice() {
        if (getRequest() == null) return "";
        StringBuffer sb = new StringBuffer();
        UserAgent userAgent = UserAgent.parseUserAgentString(getRequest().getHeader("user-agent"));

        userAgent.getBrowser().getBrowserType().getName();
        userAgent.getOperatingSystem().getDeviceType();
        sb.append(userAgent.getOperatingSystem().getDeviceType().getName());
        sb.append("-");
        sb.append(userAgent.getOperatingSystem().getManufacturer().getName());
        sb.append("-");
        sb.append(userAgent.getBrowser().getBrowserType().getName());
        sb.append("-");
        sb.append(userAgent.getBrowserVersion());
        sb.append("-");
        sb.append(userAgent.getBrowser().getRenderingEngine().name());
        return sb.toString();
    }

//    /**
//     * 限制请求次数
//     *
//     * @param event
//     * @param count
//     * @return
//     */
//    public boolean stintCount(String event, int count) {
//
//        if (ObjectUtils.isEmpty(event)) {
//            log.error("event 不能为空！");
//            return true;
//        }
//        int number = ObjectUtils.toInt(getRequest().getSession().getAttribute(event));
//        if (number >= count) return true;
//        getRequest().getSession().setAttribute(event, ++number);
//        return false;
//    }
//
//    /**
//     * 限制请求次数
//     *
//     * @param event
//     * @return
//     */
//    public int stintCount(String event) {
//        int number = ObjectUtils.toInt(getRequest().getSession().getAttribute(event));
//        getRequest().getSession().setAttribute(event, ++number);
//        return number;
//    }
}
