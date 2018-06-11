package chaos.core.shiro.filter;

import chaos.core.constant.Message_;
import chaos.utils.web.model.CaseRes;
import chaos.core.model.SAccount;
import chaos.core.service.SAccountService_;
import chaos.core.shiro.MobileAuthenticationToken;
import chaos.utils.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MobileFormAuthenticationFilter extends AccessControlFilter {

    @Autowired
    SAccountService_ sAccountService;


    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        Subject subject = getSubject(servletRequest, servletResponse);
        /*已经登录不处理*/
        if (subject.isAuthenticated() || subject.isRemembered()) return true;
//        if (isLoginRequest(servletRequest,servletResponse)) {
//
//        }
//        Subject
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        if (StringUtils.isEmpty(token)) token = req.getParameter("token");
        if (StringUtils.isEmpty(token)) token = String.valueOf(req.getAttribute("token"));
        if (StringUtils.isEmpty(token) || token.equals("null")) return true;

        SAccount account = sAccountService.byTokenAccount(token);
        if (account == null) return true;

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(account.getUsername(), account.getPassword());
        try {
            //5、委托给Realm进行登录
//            SecurityUtils.getSubject().login(authenticationToken);
            subject.login(authenticationToken);
            subject.getSession().setAttribute("account", account);
        } catch (Exception e) {
            onLoginFail(servletResponse); //6、登录失败
            return false;
        }
        return true;
    }


    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.getWriter().write(JsonUtils.tojSON(CaseRes.message(Message_.account.notExist_acount)));
//        httpResponse.getWriter().write("login error");

//        httpResponse.

    }
}