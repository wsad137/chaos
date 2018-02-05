package chaos.core.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

/**
 * Created by chaos on 2018/1/24.
 * 作者：王健
 * qq:1413221142
 */
public class CheckUtils_ {
    private final static Logger log = LoggerFactory.getLogger(CheckUtils_.class);

    private static CheckUtils_ instance;

    private static CheckUtils_ getInstance() {
        return instance;
    }

    private CheckUtils_() {
        if (instance == null) instance = new CheckUtils_();
    }

    private ApplicationContext context;

    public void init(ApplicationContext context) {
        this.context = context;
    }

    /**
     * 检查mysql 环境
     *
     * @return
     */
    public boolean mysql() {
        boolean def = false;
//        getInstance()
        return def;
    }
}
