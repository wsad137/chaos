package chaos.api.model;


import chaos.api.ApiUtils_;
import chaos.api.annoatation.*;
import chaos.utils.Md5Utils;
import chaos.utils.object.ObjectUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    /**
     * 接口标识
     */
    private String id = RandomStringUtils.randomAlphabetic(10);
    /**
     * 组id
     */
    private String idGroup;
    /**
     * 组名称
     */
    private String nameGroup;
    /**
     * url地址
     */
    private String url;
    /**
     * 接口名称
     */
    private String name;
    /**
     * 接口描述
     */
    private String desc;
    /**
     * 方法
     */
    @JsonIgnore
    private Method method;

    private String[] fieldStr = {};
    @JsonIgnore
    private Class[] beans = {};

    private String[] exclude = {};
    private List<ApiFieldModel> fields = Lists.newArrayList();
    @JsonIgnore
    private List<F> _fields = Lists.newArrayList();
    private List<ApiFieldModel> returnFields = new ArrayList<>();
    @JsonIgnore
    private List<ApiRes> _res = Lists.newArrayList();
    private List<ApiModel_> res = Lists.newArrayList();

    @JsonIgnore
    private Dict dict_ = null;

    private ApiModel_ dict;

    @JsonIgnore
    private List<Dict> dicts_ = Lists.newArrayList();

    private List<ApiModel_> dicts = Lists.newArrayList();

    public List<Dict> getDicts_() {
        return dicts_;
    }

    public void setDicts_(List<Dict> dicts_) {
        this.dicts_ = dicts_;
    }

    public List<ApiModel_> getDicts() {
        return dicts;
    }

    public void setDicts(List<ApiModel_> dicts) {
        this.dicts = dicts;
    }

    /**
     * 核心处理 字段合并排序处理
     *
     * @param def
     */
    private void processorField(ApiModel_ def, boolean isDict) {
        Map<String, ApiFieldModel> temp = new HashMap<>();
        //添加bean中的字段
        for (Class bean : def.getBeans()) {
            ApiModel apiModel = (ApiModel) bean.getAnnotation(ApiModel.class);
            if (apiModel == null) continue;
            if (isDict) {/*字典包含实体类名称和说明*/
                def.setName(StringUtils.isBlank(apiModel.value()) ? apiModel.name() : apiModel.value());
                def.setDesc(apiModel.desc());
            }
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
        if (!CollectionUtils.isEmpty(Collections.singletonList(def.get_fields()))) {
            List<ApiFieldModel> models = def.get_fields().stream().map(ApiFieldModel::new).collect(Collectors.toList());
            def.fields.addAll(models);
        }
//        if (!CollectionUtils.isEmpty(Collections.singletonList(get_fields()))) {
//            List<ApiFieldModel> models = get_fields().stream().map(ApiFieldModel::new).collect(Collectors.toList());
//            def.fields.addAll(models);
//        }


        //添加自定义字段
        for (String s : def.getFieldStr()) {
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
        for (String s : def.getExclude()) temp.remove(s);

        String globalIgnore = ApiUtils_.getConfig().getGlobalIgnore();

        String[] strings = new String[0];
        if (!StringUtils.isEmpty(globalIgnore)) strings = StringUtils.split(globalIgnore, ",");
        for (String s : strings) temp.remove(s);

        /*去除重复字段*/
        Set<String> names = def.fields.stream().map(ApiFieldModel::getName).collect(Collectors.toSet());
        List<ApiFieldModel> tSet = temp.values().stream().filter(t -> !names.contains(t.getName())).collect(Collectors.toList());
        def.fields.addAll(tSet);

//        ComparatorUtils.sort(paramModels, "name", true);
//        def.fields.sort(Comparator.comparing());
        def.fields.sort((o1, o2) -> {
            if (o1.getName().length() > o2.getName().length()) return 1;
            if (o1.getName().length() == o2.getName().length()) return 0;
            if (o1.getName().length() < o2.getName().length()) return -1;

            return 0;
        });
    }

    public Dict getDict_() {
        return dict_;
    }

    public void setDict_(Dict dict_) {
        this.dict_ = dict_;
    }

    public ApiModel_ getDict() {
        return dict;
    }

    public void setDict(ApiModel_ dict) {
        this.dict = dict;
    }

    /**
     * api 注解解析处理器
     *
     * @param groupModel
     * @return
     */
    public ApiModel_ processor(ApiGroupModel groupModel) {

        ApiModel_ def = new ApiModel_();
        def.idGroup = groupModel.getId();
        def.name = getName();
        def.nameGroup = getNameGroup();
        def.url = getUrl();
        def.generateId();
        def.exclude = getExclude();
        def._fields = get_fields();
        def.beans = getBeans();
        def.dicts_ = getDicts_();
        def.dict_ = getDict_();
        processorField(def, false);


        /*
         *返回值 注解处理
         */
//        List<ApiRes> res = get_res();
//        if (CollectionUtils.isEmpty(res)) res = Lists.newArrayList();
//        for (ApiRes apiRes : res) def.res.add(processorRes(apiRes));
        /*
         * 对象字典注解
         */
//        def.dict = processorDict();
        def.dicts = processorDicts();
        return def;
    }

    private List<ApiModel_> processorDicts() {

        List<ApiModel_> dicts = Lists.newArrayList();

        List<Dict> dicts_ = Lists.newArrayList();
        if (CollectionUtils.isEmpty(getDicts_())) dicts_ = Lists.newArrayList();
        dicts_.add(getDict_());
        for (Dict dict1 : dicts_) dicts.add(processorDict(dict1));
        return dicts;
    }

    private ApiModel_ processorDict(Dict dict) {
        ApiModel_ def = new ApiModel_();
        def.beans = new Class[]{dict.value()};
        def._fields = Arrays.asList(dict.fs());
        processorField(def, true);
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
