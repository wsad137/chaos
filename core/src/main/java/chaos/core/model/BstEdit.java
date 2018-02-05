package chaos.core.model;


import chaos.core.constant.Message_;
import chaos.utils.json.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 转换 bootstrap-table x-edittable 编辑对象
 * Created by chaos on 2017/3/30.
 * 作者：王健
 * qq:1413221142
 */
public class BstEdit {
//    private final TypeToken<T> typeToken = new TypeToken<T>(getClass()) {
//    };
    //    private final Type type = typeToken.getRawType(); // or getRawType() to return Class<? super T>
//    private final Type type = typeToken.getType(); // or getRawType() to return Class<? super T>

//    public Type getType() {
//        return type;
//    }

    private Logger logger = LoggerFactory.getLogger(BstEdit.class);


    /**
     * 需要修改的字段
     */
    private String field;
    /**
     * id
     */
    private Long id;
    /**
     * 新内容
     */
    private String newValue;
    /**
     * 旧内容
     */
    private String oldValue;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }


//    private Class getType(T... t) {
//        return t.getClass().getComponentType();
//    }

    /**
     * 返回 转换后的对象
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> T toTagerModel(Class<T> t) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JsonUtils.tojSON(t.newInstance()));
            jsonObject.put(getField(), getNewValue());
            // TODO: 2017/3/30 写死了 唯一标识字段
            jsonObject.put("id", getId());
        } catch (JSONException | IllegalAccessException | InstantiationException e) {
            logger.error(Message_._controller.empty_param);
            e.printStackTrace();
        }
        return JsonUtils.toObj(jsonObject.toString(), t);
    }

}
