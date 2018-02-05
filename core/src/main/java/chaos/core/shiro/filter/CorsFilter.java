package chaos.core.shiro.filter;

import chaos.core.service.SAccountService_;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter extends UserFilter {

    @Autowired
    SAccountService_ accountService;


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        HttpServletRequest request1 = WebUtils.toHttp(servletRequest);
        HttpServletResponse response1 = (HttpServletResponse) servletResponse;
        if (request1.getMethod().equalsIgnoreCase(RequestMethod.OPTIONS.name())) {
//            response1.setContentType("application/json");
//            response1.setCharacterEncoding("utf-8");
////            response1.addHeader("Access-Control-Allow-Origin", "*");
//            response1.addHeader("Access-Control-Allow-Origin", "http://localhost:3001");
//            response1.addHeader("Access-Control-Allow-Headers", "X-Custom-Header");
//            response1.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT");
//            response1.addHeader("Access-Control-Allow-Credentials", "true");
//            response1.addHeader("Access-Control-Expose-Headers", "token");
//            response1.addHeader("Access-Control-Max-Age", "1728000");
            return false;
        }
        return super.isAccessAllowed(servletRequest, servletResponse, o);
//        return false;
    }


}