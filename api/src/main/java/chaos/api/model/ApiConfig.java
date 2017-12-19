package chaos.api.model;

import chaos.api.constant._ApiKey;
import chaos.api.constant._ApiProperties;
import chaos.utils.object.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-22
 */
public class ApiConfig {


    private boolean autoInit = ObjectUtils.toBool(_ApiProperties._chaos_api.getString(_ApiKey._propertie.api.autoInit, "true"));
    private String rootPath = _ApiProperties._chaos_api.getString(_ApiKey._propertie.api.rootPath);
    private String name = _ApiProperties._chaos_api.getString(_ApiKey._propertie.api.name, "chaosApi");
    private String globalIgnore = _ApiProperties._chaos_api.getString(_ApiKey._propertie.api.globalIgnore);

    public boolean isAutoInit() {
        return autoInit;
    }

    public void setAutoInit(boolean autoInit) {
        this.autoInit = autoInit;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public String getName() {
        if (StringUtils.isEmpty(name)) name = "chaosApi";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlobalIgnore() {
        return globalIgnore;
    }

    public void setGlobalIgnore(String globalIgnore) {
        this.globalIgnore = globalIgnore;
    }
}
