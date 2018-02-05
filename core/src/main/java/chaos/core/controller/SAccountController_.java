package chaos.core.controller;

import chaos.api.annoatation.Api;
import chaos.api.annoatation.ApiGroup;
import chaos.core.commons.KaptchaUtils_;
import chaos.core.commons.SAccountUtils_;
import chaos.core.constant.K_;
import chaos.core.constant.Message_;
import chaos.core.constant.P_;
import chaos.core.model.SAccount;
import chaos.core.service.SAccountService_;
import chaos.utils.object.ObjectUtils;
import chaos.utils.web.model.CaseRes;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by chaos on 2018/1/16.
 * 作者：王健
 * qq:1413221142
 */
@ApiGroup("账户_")
@RequestMapping("/account_")
@Controller
public class SAccountController_ extends BaseCoreController {

    private final static Logger log = LoggerFactory.getLogger(SAccountController_.class);

    @Autowired
    SAccountService_ sAccountService;


    /**
     * 测试返回JSON数据
     */
    @Api(name = "登录", fieldStr = {"username///admin", "password///123456", "iCode"})
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(@RequestBody(required = false) Map map) {

        Assert.notNull(map, Message_._controller.empty_param);

        String iCode = String.valueOf(map.getOrDefault("iCode", ""));
        if (!P_.env_.getBoolean(K_.p_.env.env_isDebug)) {
            if (!KaptchaUtils_.isVerify(iCode)) return CaseRes.New().setMessage(Message_.captcha_.error);
        }

        SAccount model = ObjectUtils.mapToObject(map, SAccount.class);
        if (model == null) model = new SAccount();
//
//        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(model.getUsername(), model.getPassword(), model.getNickname());
        UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
        token.setRememberMe(true);

        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return CaseRes.data(SAccountUtils_.getSAccount());
//        return CaseRes.New();
    }


    public SAccount getSAccount() {
        Subject subject = SecurityUtils.getSubject();
        SAccount account = (SAccount) subject.getSession().getAttribute("account");
        if (account == null) throw new ShiroException("登录失败，或者已经过期！");
        return account;
    }

    public SAccount setSAccount(SAccount user) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("account", user);
        return user;
    }

}
