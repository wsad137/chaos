package chaos.weixin.example;

import chaos.weixin.WeiXinUtils_;
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

//        WeiXinUtils_.refreshToken();
//        WeiXinUtils_ weiXinHelper = new WeiXinUtils_();
        WeiXinUtils_.init(appContext);
//Ex
//        WeiXinConfig_.init(appContext);
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