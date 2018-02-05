package chaos.core.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by chaos on 2017/9/24.
 * 作者：王健
 * qq:1413221142
 */
public class RefreshAuthenticationToken extends UsernamePasswordToken {

    public RefreshAuthenticationToken(String username, String password) {
        super(username,password);
    }
}
