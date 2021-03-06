package chaos.api.model;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chaos on 2017/12/13.
 * 作者：王健
 * qq:1413221142
 */
public class ApiGroupModel {

    private final static Logger log = LoggerFactory.getLogger(ApiGroupModel.class);

    /**
     * 组标识
     */
    private String id = RandomStringUtils.randomAlphabetic(10);
    /**
     * 组名称
     */
    private String name;

    /*接口集合*/
    private List<ApiModel_> apis = Lists.newArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ApiModel_> getApis() {
        return apis;
    }

    public void setApis(List<ApiModel_> apis) {
        this.apis = apis;
    }
}
