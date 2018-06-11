package chaos.api.model;


import chaos.api.ApiUtils_;
import chaos.api.annoatation.ApiField;
import chaos.api.annoatation.ApiModel;
import chaos.api.annoatation.ApiRes;
import chaos.api.annoatation.F;
import chaos.utils.Md5Utils;
import chaos.utils.object.ObjectUtils;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

/**
 * © app
 * qq:1413221142
 * 作者：王健
 * 时间：2016-02-03
 */
public class ApiModel_ {

    private String id = RandomStringUtils.randomAlphabetic(10);
    private String idGroup;
    private String nameGroup;
    private String url;
    private String name;
    private String desc;
    private Method method;
    private String[] fieldStr = {};
    private Class[] beans = {};
    private String[] exclude = {};
    private List<ApiFieldModel> fields = Lists.newArrayList();
    private List<F> _fields = Lists.newArrayList();
    private List<ApiFieldModel> returnFields = new ArrayList<>();
    private List<ApiRes> _res = Lists.newArrayList();
    private List<ApiModel_> res = Lists.newArrayList();


    /**
     * 返回值 处理器
     *
     * @param apiRes
     * @return
     */
    private ApiModel_ processorRes(ApiRes apiRes) {
        ApiModel_ def = new ApiModel_();
        def.desc = apiRes.desc();
        processorField(def);
        return def;
    }

    /**
     * 核心处理 字段合并排序处理
     *
     * @param def
     */
    private void processorField(ApiModel_ def) {
        Map<String, ApiFieldModel> temp = new HashMap<>();
        //添加bean中的字段
        for (Class bean : getBeans()) {
            ApiModel apiModel = (ApiModel) bean.getAnnotation(ApiModel.class);
            if (apiModel == null) continue;
            for (Field field : bean.getDeclaredFields()) {
                ApiField apiField = field.getAnnotation(ApiField.class);
                if (apiField == null && !apiModel.autoSca()) continue;
                if (apiField != null && apiField.exclude()) continue;
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) continue;

                if (field.isSynthetic()) continue;
                ApiFieldModel paramModel = new ApiFieldModel(field);
                temp.put(field.getName(), paramModel);
            }
        }

        // TODO 根据参数添加需要的字段
//        for (Parameter parameter : method.getParameters()) {
//            Class cla = parameter.getType();
//            if (cla.getTypeName().contains("java")) continue;
//            if (cla.isPrimitive()) continue;
//
//            for (Field field : cla.getDeclaredFields()) {
//                ParamModel paramModel = new ParamModel(field);
//                temp.put(field.getName(), paramModel);
//            }
//        }

        /*
         *添加注解字段
         */
        if (!CollectionUtils.isEmpty(Collections.singletonList(get_fields()))) {
            List<ApiFieldModel> models = get_fields().stream().map(ApiFieldModel::new).collect(Collectors.toList());
            def.fields.addAll(models);
        }


        //添加自定义字段
        for (String s : getFieldStr()) {
            ApiFieldModel paramModel = new ApiFieldModel(s);

            if (temp.containsKey(paramModel.getName())) {
                ApiFieldModel p = temp.get(paramModel.getName());
                if (!ObjectUtils.isEmpty(paramModel.getDef())) p.setDef(paramModel.getDef());
                if (!ObjectUtils.isEmpty(paramModel.getDesc())) p.setDesc(paramModel.getDesc());
                if (!ObjectUtils.isEmpty(paramModel.getType())) p.setType(paramModel.getType());
            } else {
                temp.put(paramModel.getName(), paramModel);
            }
        }


        //排除不需要的字段
        for (String s : getExclude()) temp.remove(s);

        String globalIgnore = ApiUtils_.getConfig().getGlobalIgnore();

        String[] strings = new String[0];
        if (!StringUtils.isEmpty(globalIgnore)) strings = StringUtils.split(globalIgnore, ",");
        for (String s : strings) temp.remove(s);

        def.fields.addAll(new ArrayList<>(temp.values()));

//        ComparatorUtils.sort(paramModels, "name", true);
//        def.fields.sort(Comparator.comparing());
        def.fields.sort((o1, o2) -> {
            if (o1.getName().length() > o2.getName().length()) return 1;
            if (o1.getName().length() == o2.getName().length()) return 0;
            if (o1.getName().length() < o2.getName().length()) return -1;

            return 0;
        });
    }

    public ApiModel_ processor(ApiGroupModel groupModel) {
        ApiModel_ def = new ApiModel_();
        def.idGroup = groupModel.getId();
        def.name = getName();
        def.nameGroup = getNameGroup();
        def.url = getUrl();
        def.generateId();
        processorField(def);


        /*
        *返回值 注解处理
        */
        List<ApiRes> res = get_res();
        if (CollectionUtils.isEmpty(res)) res = Lists.newArrayList();
        for (ApiRes apiRes : res) def.res.add(processorRes(apiRes));

        return def;
    }


    public String[] getFieldStr() {
        return fieldStr;
    }

    public void setFieldStr(String[] fieldStr) {
        this.fieldStr = fieldStr;
    }

    public List<ApiFieldModel> getFields() {
        return fields;
    }

    public void setFields(List<ApiFieldModel> fields) {
        this.fields = fields;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class[] getBeans() {
        return beans;
    }

    public void setBeans(Class[] beans) {
        this.beans = beans;
    }

    public String[] getExclude() {
        return exclude;
    }

    public void setExclude(String[] exclude) {
        this.exclude = exclude;
    }

    public List<F> get_fields() {
        return _fields;
    }

    public void set_fields(List<F> _fields) {
        this._fields = _fields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<ApiRes> get_res() {
        return _res;
    }

    public void set_res(List<ApiRes> _res) {
        this._res = _res;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Method getMethod() {
        return method;
    }

    public List<ApiFieldModel> getReturnFields() {
        return returnFields;
    }

    public void setReturnFields(List<ApiFieldModel> returnFields) {
        if (returnFields == null) return;
        this.returnFields = returnFields;
    }

    public List<ApiModel_> getRes() {
        return res;
    }

    public void setRes(List<ApiModel_> res) {
        this.res = res;
    }

    /**
     * 创建id
     */
    public void generateId() {
//        String s = Base64.getEncoder().encodeToString((String.valueOf(getName()) + String.valueOf(getUrl()) + String.valueOf(getNameGroup())).getBytes());
        String s = Md5Utils.toMd5((String.valueOf(getName()) + String.valueOf(getUrl()) + String.valueOf(getNameGroup())));
        setId(s);
    }
}
