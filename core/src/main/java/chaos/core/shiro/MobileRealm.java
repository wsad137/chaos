package chaos.core.shiro;

import chaos.core.constant.Message_;
import chaos.core.model.SAccount;
import chaos.core.service.SAccountService_;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chaos on 2017/11/1.
 * 作者：王健
 * qq:1413221142
 */
public class MobileRealm extends AuthorizingRealm {

    @Autowired
    private SAccountService_ sAccountService;

    /*授权*/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*认证*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        SimpleAuthenticationInfo info = null;
        SAccount account = null;

        /*移动端token登录*/
        if (authenticationToken instanceof MobileAuthenticationToken) {
            MobileAuthenticationToken mToken = (MobileAuthenticationToken) authenticationToken;

//            account = sAccountService.byTokenAccount(mToken.getUsername());
//            if (account == null) throw new IncorrectCredentialsException(Message_.account.notExist_acount);
            info = new SimpleAuthenticationInfo(mToken.getPrincipal(), mToken.getPassword(), String.valueOf(mToken.getPrincipal()));
//            info = new SimpleAuthenticationInfo(account.getUsername(), account.getPassword(), account.getNickname());
//            SecurityUtils.setSecurityManager();
        }
        return info;
    }
}
