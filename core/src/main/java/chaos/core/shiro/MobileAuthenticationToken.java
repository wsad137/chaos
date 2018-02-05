package chaos.core.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by chaos on 2017/9/24.
 * 作者：王健
 * qq:1413221142
 */
public class MobileAuthenticationToken extends UsernamePasswordToken {

    public MobileAuthenticationToken(String username, String password) {
        super(username,password);
    }
}
