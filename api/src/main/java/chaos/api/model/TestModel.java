package chaos.api.model;

import chaos.api.annoatation.ApiModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chaos on 2017/12/15.
 * 作者：王健
 * qq:1413221142
 */
@ApiModel
public class TestModel {
    private final static Logger log = LoggerFactory.getLogger(TestModel.class);

    private int id;
    private String name;
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
