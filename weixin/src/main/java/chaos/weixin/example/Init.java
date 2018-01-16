package chaos.weixin.example;

import chaos.weixin._WeiXinHelper;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {


    private static final Logger log = Logger.getLogger(Init.class);

    public ApplicationContext appContext;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        appContext = event.getApplicationContext();

//        _WeiXinHelper.refreshToken();
        _WeiXinHelper weiXinHelper = new _WeiXinHelper();
        weiXinHelper.init(appContext);
//Ex
//        ConfigHelper.init(appContext);
//        KaptchaHelper.init(appContext);
//        CommonRequestHelper.init(appContext);
//_WeXinHelper
//        WeChatHelper weChatHelper = new WeChatHelper(appContext, Key.propertie.jiyou);
//
//        ApiConfig apiConfig = ApiHelper.getInstance().getConfig();
//        apiConfig.getExcludeField().add("ct");
//        apiConfig.getExcludeField().add("ut");
//        apiConfig.getExcludeField().add("createTime");
//        apiConfig.getExcludeField().add("updateTime");
//        apiConfig.getExcludeField().add("serialVersionUID");
//        ApiHelper.getInstance().refresh();
//        appContext.publishEvent();
    }
}