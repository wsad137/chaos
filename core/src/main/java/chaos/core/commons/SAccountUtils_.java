package chaos.core.commons;

import chaos.core.model.SAccount;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chaos on 2018/1/19.
 * 作者：王健
 * qq:1413221142
 */
public class SAccountUtils_ {
    private final static Logger log = LoggerFactory.getLogger(SAccountUtils_.class);

    public static SAccount getSAccount() {
        Subject subject = SecurityUtils.getSubject();
        SAccount account = (SAccount) subject.getSession().getAttribute("account");
        if (account == null) throw new ShiroException("登录失败，或者已经过期！");
        return account;
    }

    public static SAccount setSAccount(SAccount user) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("account", user);
        return user;
    }
}
