package chaos.core.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;

/**
 * Created by chaos on 2017/10/12.
 * 作者：王健
 * qq:1413221142
 */
public class MyRememberMeManager extends CookieRememberMeManager {

    public MyRememberMeManager() {
        super();
    }

    @Override
    public Cookie getCookie() {
        return super.getCookie();
    }

    @Override
    public void setCookie(Cookie cookie) {
        super.setCookie(cookie);
    }

    @Override
    protected void rememberSerializedIdentity(Subject subject, byte[] serialized) {
        super.rememberSerializedIdentity(subject, serialized);
    }

    @Override
    protected byte[] getRememberedSerializedIdentity(SubjectContext subjectContext) {
        return super.getRememberedSerializedIdentity(subjectContext);
    }

    @Override
    protected void forgetIdentity(Subject subject) {
        super.forgetIdentity(subject);
    }

    @Override
    public void forgetIdentity(SubjectContext subjectContext) {
        super.forgetIdentity(subjectContext);
    }
}
