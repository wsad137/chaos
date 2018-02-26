package chaos.api.context;

import chaos.api.ApiUtils_;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApiInit_ implements ApplicationListener<ContextRefreshedEvent> {


//    @Value("${file.upload}")
//    @Value("${mail.send}")
//    private boolean email_send;

    private static final Logger log = Logger.getLogger(ApiInit_.class);
    public ApplicationContext appContext;

    //  private static Scanner sc = new Scanner(System.in);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        appContext = event.getApplicationContext();
        System.out.println(appContext.getId());

        log.info("api-web初始化");
        try {
            ApiUtils_.init(appContext);
        } catch (Exception e) {
            log.warn("初始化异常！", e);
        }
        log.info("api-web初始化完成");

    }


    public void init(ApplicationContext appContext) {

    }

    public static void main(String[] args) {

    }
}