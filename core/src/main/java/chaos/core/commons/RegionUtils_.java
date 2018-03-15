package chaos.core.commons;

import chaos.core.model.RegionModel_;
import chaos.core.service.RegionService_;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.List;

/**
 * ©土土网 app-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-26
 */
public class RegionUtils_ {

    private static RegionUtils_ instance;
    private ApplicationContext context;
    private RegionService_ service;


    public static RegionUtils_ getInstance() {
        Assert.notNull(instance, "RegionUtils_ 还未初始化！");
        return instance;
    }

    private RegionUtils_(ApplicationContext context) {
        this.context = context;
        this.service = this.context.getBean(RegionService_.class);
    }

    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
        if (instance != null) return;
        instance = new RegionUtils_(context);
    }

    public RegionService_ getService() {
        return service;
    }

    public static List<RegionModel_> getRegion(int pId) {
        return getInstance().service.getRegion(pId);
    }
}
