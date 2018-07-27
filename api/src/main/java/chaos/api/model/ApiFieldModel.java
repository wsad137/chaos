package chaos.api.model;

import chaos.api.annoatation.ApiField;
import chaos.api.annoatation.F;
import chaos.utils.json.JsonUtils;
import chaos.utils.object.ObjectUtils;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

/**
 * ©chaos
 * qq:1413221142
 * 作者：王健
 * 时间：2016-02-18
 */
public class ApiFieldModel {

    private final static Logger log = LoggerFactory.getLogger(ApiFieldModel.class);
    /**
     * 参数名称
     */
    private String name = "";
    /**
     * 参数类型
     */
//    public TtwParamEnum type = TtwParamEnum.STRING;
    private String type = "string";
    /**
     * 参数说明
     */
    private String desc = "";
    /**
     * 默认值
     */
    private String def = "";


    /**
     * 是否非可以为空
     *
     * @return
     */
    private Boolean notEmpty;
    /**
     * 长度
     *
     * @return
     */
    private Integer lang;


    public void setNotEmpty(Boolean notEmpty) {
        this.notEmpty = notEmpty;
    }

    public Integer getLang() {
        return lang;
    }

    public Boolean getNotEmpty() {
        return notEmpty;
    }

    public void setLang(Integer lang) {
        this.lang = lang;
    }


    public ApiFieldModel() {
    }

    /**
     * 字符串参数格式化
     *
     * @param params
     */
    public ApiFieldModel(String params) {
        if (!ObjectUtils.isEmpty(params)) {
//            String[] temp = params.split("/");
            //最多切割四个
            String[] temp = StringUtils.splitByWholeSeparatorPreserveAllTokens(params, "/", 4);
            if (!ObjectUtils.isEmpty((Object) temp)) {
                for (int i = 0; i < temp.length; i++) {
                    if (ObjectUtils.isEmpty(temp[i])) {
                        continue;
                    }
                    switch (i) {
                        case 0:
                            this.name = temp[i];
                            break;
                        case 1:
                            this.desc = temp[i];
                            break;
                        case 2:
                            this.type = temp[i];
                            break;
                        case 3:
                            this.def = temp[i];
//                            //如果默认值等于time 返回时间戳
//                            this.def = (temp[i].equals("time") ? System.currentTimeMillis() + "" : temp[i]);
                            break;
                    }

                }
            }
        }
    }

    /**
     * 字段格式化
     *
     * @param field
     */
    public ApiFieldModel(Field field) {
        setName(field.getName());
        String[] split = StringUtils.split(field.getGenericType().getTypeName(), ".");
        setType(split[split.length - 1].toLowerCase());
//        try {
//            setDef(ObjectUtils.toString(field.get(srcObj)));
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        setDef(field.);

        ApiField apiField = field.getAnnotation(ApiField.class);
        if (apiField == null) return;
        if (!StringUtils.isEmpty(apiField.name())) setName(apiField.name());
        if (!StringUtils.isEmpty(apiField.value())) setName(apiField.value());
        if (!StringUtils.isEmpty(apiField.def())) setDef(apiField.def());
        if (!StringUtils.isEmpty(apiField.desc())) setDesc(apiField.desc());
        if (!StringUtils.isEmpty(apiField.type())) setType(apiField.type());
        if (!ObjectUtils.isEmpty(apiField.notEmpty())) setNotEmpty(apiField.notEmpty());
        if (!ObjectUtils.isEmpty(apiField.lang())) setLang(apiField.lang());
//        if (!StringUtils.isEmpty(apiField.lang())) (apiField.type());
    }

    /**
     * 字段格式化
     */
    public ApiFieldModel(F apiField) {
        if (apiField == null) return;
        if (!StringUtils.isEmpty(apiField.value())) setName(apiField.value());
        if (!StringUtils.isEmpty(apiField.name())) setName(apiField.name());

        setDef(apiField.def());
        setDesc(apiField.desc());
        setType(apiField.type());
        setLang(apiField.lang());
        setNotEmpty(apiField.notEmpty());
    }


    public String getName() {
        return name;
    }

    public String getType() {

        if (ObjectUtils.isEmpty(type)) return type;

        if (type.equals(String.class.getName())) {
            type = "text";
        } else if (type.equals(Short.class.getName()) || type.equals(int.class.getName()) || type.equals(Integer.class.getName())) {
            type = "number";
        }
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public String getDef() {
        if (def == null) return def;

        if (def.contains(".class")) {
            def = toJson(def);
        }

        return def;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public static void main(String[] args) {
//        System.out.println(Short.class.getName());
    }


    private String toJson(String calssName) {
        String def = calssName;
        try {
            List<File> fils = (List<File>) FileUtils.listFiles(FileUtils.toFile(ApiFieldModel.class.getResource("/")), FileFilterUtils.nameFileFilter(calssName), DirectoryFileFilter.INSTANCE);
            if (ObjectUtils.isEmpty(fils)) return def;
            File fil = fils.get(0);
            String[] temp = StringUtils.split(findPackageName("", fil.getParentFile()), ".");
            ArrayUtils.reverse(temp);
            String className = StringUtils.join(temp, ".") + "." + Files.getNameWithoutExtension(fil.getName());
            def = JsonUtils.tojSON(Class.forName(className).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return def;
    }

    /**
     * 根据文件 获取包名
     *
     * @param pname
     * @param parent
     * @return
     */
    public static String findPackageName(String pname, File parent) {
        if (!parent.getName().equals("classes")) {
            pname += parent.getName() + ".";
            return findPackageName(pname, parent.getParentFile());
        }
        return pname;
    }

}


