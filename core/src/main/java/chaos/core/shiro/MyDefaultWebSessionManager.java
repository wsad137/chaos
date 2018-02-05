package chaos.core.shiro;

import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Created by chaos on 2017/10/13.
 * 作者：王健
 * qq:1413221142
 */
public class MyDefaultWebSessionManager extends DefaultWebSessionManager {

    public MyDefaultWebSessionManager() {
        super();
    }

    @Override
    public Cookie getSessionIdCookie() {
        return super.getSessionIdCookie();
    }

    @Override
    public void setSessionIdCookie(Cookie sessionIdCookie) {
        super.setSessionIdCookie(sessionIdCookie);
    }

    @Override
    public boolean isSessionIdCookieEnabled() {
        return super.isSessionIdCookieEnabled();
    }

    @Override
    public void setSessionIdCookieEnabled(boolean sessionIdCookieEnabled) {
        super.setSessionIdCookieEnabled(sessionIdCookieEnabled);
    }

    @Override
    public boolean isSessionIdUrlRewritingEnabled() {
        return super.isSessionIdUrlRewritingEnabled();
    }

    @Override
    public void setSessionIdUrlRewritingEnabled(boolean sessionIdUrlRewritingEnabled) {
        super.setSessionIdUrlRewritingEnabled(sessionIdUrlRewritingEnabled);
    }

    @Override
    protected Session createExposedSession(Session session, SessionContext context) {
        return super.createExposedSession(session, context);
    }

    @Override
    protected Session createExposedSession(Session session, SessionKey key) {
        return super.createExposedSession(session, key);
    }

    @Override
    protected void onStart(Session session, SessionContext context) {
        super.onStart(session, context);
    }

    @Override
    public Serializable getSessionId(SessionKey key) {
        return super.getSessionId(key);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return super.getSessionId(request, response);
    }

    @Override
    protected void onExpiration(Session s, ExpiredSessionException ese, SessionKey key) {
        super.onExpiration(s, ese, key);
    }

    @Override
    protected void onInvalidation(Session session, InvalidSessionException ise, SessionKey key) {
        super.onInvalidation(session, ise, key);
    }

    @Override
    protected void onStop(Session session, SessionKey key) {
        super.onStop(session, key);
    }

    @Override
    public boolean isServletContainerSessions() {
        return super.isServletContainerSessions();
    }
}
