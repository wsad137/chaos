package chaos.api.model;

import chaos.api.annoatation.ApiField;
import chaos.api.annoatation.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chaos on 2017/12/15.
 * 作者：王健
 * qq:1413221142
 */
@ApiModel(value = "value", name = "name", desc = "desc")
public class TestModel {
    private final static Logger log = LoggerFactory.getLogger(TestModel.class);
    @ApiField(desc = "id", def = "001")
    private int id;
    @ApiField(desc = "用户名", def = "hahah", lang = 100)
    private String name;
    @ApiField(desc = "bool", def = "true", notEmpty = true, lang = 50)
    private boolean bool;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }
}
