package chaos.core.shiro;

import chaos.core.constant.Message_;
import chaos.core.model.SAccount;
import chaos.core.service.SAccountService_;
import chaos.utils.Md5Utils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

/**
 * Created by chaos on 2017/6/11.
 * 作者：王健
 * qq:1413221142
 */
public class PcRealm extends AuthorizingRealm {


    @Autowired
    SAccountService_ sAccountService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        return info;
    }

    /**
     * 认证登录
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = null, password = "";
        SimpleAuthenticationInfo info = null;
        SAccount account = null;
        /*刷新session登录*/
        if (authenticationToken instanceof RefreshAuthenticationToken) {
            RefreshAuthenticationToken mToken = (RefreshAuthenticationToken) authenticationToken;
            account = sAccountService.byTokenAccount(mToken.getUsername());
            if (account == null) throw new IncorrectCredentialsException(Message_.account.notExist_acount);
            info = new SimpleAuthenticationInfo(account.getUsername(), account.getPassword(), account.getNickname());
        }
        /*普通账号密码登录*/
        if (authenticationToken instanceof UsernamePasswordToken) {
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            username = token.getUsername();
            if (token.getPassword() != null) password = String.valueOf(token.getPassword());

            account = sAccountService.byNameAccount(username);

            if (account == null) throw new AuthenticationException(Message_.account.notExist_acount);
            if (password.length() < 32) password = Md5Utils.toMd5(password);
            if (!account.getPassword().equals(password)) throw new IncorrectCredentialsException(Message_.account.error_password);

            account.setToken(Md5Utils.toMd5(RandomStringUtils.randomAscii(32) + Instant.now().toEpochMilli()));
            account.setlT(Instant.now().toEpochMilli());
            sAccountService.addOrUpdateAccount(account);
            info = new SimpleAuthenticationInfo(username, password, account.getNickname());

        }
        SecurityUtils.getSubject().getSession().setAttribute("account", account);
        return info;
    }


}
