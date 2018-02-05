package chaos.core.commons;

import chaos.core.service.ExceptionService_;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

/**
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-23
 */
public class ExceptionUtils_ {
    private static ExceptionUtils_ instance;

    private static ExceptionUtils_ getInstance() {
        Assert.notNull(instance, "ExcptionHelper 还未初始化！");
        return instance;
    }

    private ExceptionUtils_(ApplicationContext context) {
        this.context = context;
        this.exceptionService = this.context.getBean(ExceptionService_.class);
    }

    private ApplicationContext context;

    private static ExceptionService_ exceptionService;

    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
        if (instance != null) return;
        instance = new ExceptionUtils_(context);
    }

    public ExceptionService_ getExceptionService() {
        return exceptionService;
    }

    /**
     * 添加异常
     *
     * @param ex
     * @return
     */
    public static boolean add(Exception ex) {
        return exceptionService.insertExceptionModel(ex);
    }
}
