package chaos.core.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

/**
 * Created by chaos on 2017/11/1.
 * 作者：王健
 * qq:1413221142
 */
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator{
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) {
        return super.doMultiRealmAuthentication(realms, token);

//
//
//        AuthenticationStrategy strategy = getAuthenticationStrategy();
//        AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);
//
//        for (Realm realm : realms) {
//            aggregate = strategy.beforeAttempt(realm, token, aggregate);
//            if (realm.supports(token)) {
//                AuthenticationInfo info = null;
//                Throwable t = null;
//                try {
//                    info = realm.getAuthenticationInfo(token);
//                } catch (Throwable throwable) {
//                    t = throwable;
//                }
//                aggregate = strategy.afterAttempt(realm, token, info, aggregate, t);
//                // dirty dirty hack
//                if (aggregate != null && !CollectionUtils.isEmpty(aggregate.getPrincipals())) {
//                    return aggregate;
//                }
//                // end dirty dirty hack
//            } else {
//
//            }
//        }
//        aggregate = strategy.afterAllAttempts(token, aggregate);
//        return aggregate;
    }

    @Override
    public void setAuthenticationStrategy(AuthenticationStrategy authenticationStrategy) {
        super.setAuthenticationStrategy(authenticationStrategy);
    }

}
