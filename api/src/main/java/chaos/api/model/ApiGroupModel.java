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

    private String id = RandomStringUtils.randomAlphabetic(10);
    private String name;
    private List<_ApiModel> apis = Lists.newArrayList();

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

    public List<_ApiModel> getApis() {
        return apis;
    }

    public void setApis(List<_ApiModel> apis) {
        this.apis = apis;
    }
}
